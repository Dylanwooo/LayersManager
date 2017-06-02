/**
 * User: yuhao
 * Date: 14-12-4
 * Time: 上午10:37
 * render常用操作方法
 */

define(
    [
        "dojo/_base/declare",
        "dojo/_base/array",
        "dojo/_base/Color",
        "esri/symbols/Font",
        "esri/symbols/SimpleFillSymbol",
        "esri/symbols/SimpleLineSymbol",
        "esri/symbols/SimpleMarkerSymbol",
        "esri/symbols/PictureMarkerSymbol",
        "esri/symbols/TextSymbol",
        "esri/renderers/ClassBreaksRenderer",
        "esri/renderers/SimpleRenderer",
        "esri/renderers/UniqueValueRenderer",
        "./symbolUtil",
        "./utils"
    ],
    function (declare, array, Color, Font, SimpleFillSymbol, SimpleLineSymbol, SimpleMarkerSymbol, PictureMarkerSymbol, TextSymbol, ClassBreaksRenderer, SimpleRenderer, UniqueValueRenderer, symbolUtil, utils) {

        return {

            /**
             * 获取点渲染器
             * @param opt
             */
            getMarkerRenderer:function (opt) {
                var type = SimpleMarkerSymbol.STYLE_CIRCLE,
                    color = new Color(opt.symbol.color),
                    size = opt.symbol.size,
                    border = opt.symbol.border,
                    outline = null,
                    borderColor, symbol, shadowSymbol, renderer;

                if (border > 0) {
                    borderColor = new Color(opt.symbol.borderColor);
                    outline = new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, borderColor, border);
                }
                switch (opt.symbol.shape) {
                    case "square":
                        type = SimpleMarkerSymbol.STYLE_SQUARE;
                        break;
                    case "diamond":
                        type = SimpleMarkerSymbol.STYLE_DIAMOND;
                        break;
                    default:
                        break;
                }
                symbol = new SimpleMarkerSymbol(type, size, outline, color);
                renderer = new SimpleRenderer(symbol);
                return renderer;
            },

            /**
             * 获取logo渲染器
             * @param opt
             */
            getLogoRenderer:function (opt) {
                var sym = opt.symbol;

                if (sym) {
                    var symbol = new PictureMarkerSymbol(sym.path, sym.width * sym.scale, sym.height * sym.scale);
                    return new SimpleRenderer(symbol);
                }
                return null;
            },

            /**
             * 获取图片渲染器
             * @param opt
             */
            getPictureRenderer:function (opt) {
                var sym = opt.symbol;

                if (sym) {
                    var symbol = new PictureMarkerSymbol(sym.path, sym.width * sym.scale, sym.height * sym.scale);
                    return new SimpleRenderer(symbol);
                }
                return null;
            },

            /**
             * 获取填充渲染器
             * @param args
             */
            getFillRenderer:function (args) {
                var symbol = args.symbol,
                    color = new Color(symbol.color),
                    fillStyle = null,
                    border = symbol.border,
                    outline = null;

                if (symbol.color === "none")
                    fillStyle = SimpleLineSymbol.STYLE_NULL;
                else
                    fillStyle = SimpleLineSymbol.STYLE_SOLID;
                if (border > 0) {
                    var borderColor = new Color(symbol.borderColor);
                    outline = new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, borderColor, border);
                }

                var symbol = new SimpleFillSymbol(fillStyle, outline, color);
                return new SimpleRenderer(symbol);
            },

            /**
             * 构造等级符号渲染器
             * @param sym
             * @param geo
             */
            makeGradeRenderer:function (sym, geo, opacity) {
                if (geo === "point") {
                    return this.makePointGradeRenderer(sym);

                } else if (geo === "polygon") {
                    return this.makePolygonGradeRenderer(sym, opacity);
                }
                return null;
            },

            /**
             * 构造点数据等级符号渲染器
             * @param sym
             */
            makePointGradeRenderer:function (sym) {
                var r = sym.radius,
                    dr = Math.round((r.max - r.min) / (sym.classify - 1)),
                    ranges = sym.ranges,
                    color, symbol, renderer, i;

                renderer = new ClassBreaksRenderer(false, function (g) {
                    return g.attributes[sym.field];
                });
                renderer.attributeField = sym.field;
                color = new Color(sym.color);

                // 添加第一个分级
                symbol = symbolUtil.getMarkerSymbol(r.min, color);

                if (ranges[0][0] === 0) {    // 如果最小值是0，0值不显示，因此设置一个比0稍大的极小数值
                    renderer.addBreak(1E-10, ranges[0][1], symbol);
                } else {
                    renderer.addBreak(-Infinity, ranges[0][1], symbol);
                }
                // 添加中间分级
                for (i = 1; i < sym.classify - 1; ++i) {
                    symbol = symbolUtil.getMarkerSymbol(r.min + i * dr, color);
                    renderer.addBreak(ranges[i][0], ranges[i][1], symbol);
                }
                // 添加最后一个分级
                symbol = symbolUtil.getMarkerSymbol(r.max, color);

                if (ranges[i][1] === 0) {
                    renderer.addBreak(ranges[i][0], 0, symbol);
                } else {
                    renderer.addBreak(ranges[i][0], Infinity, symbol);
                }
                return renderer;
            },

            /**
             * 构造多边形等级符号渲染器
             * @param sym
             */
            makePolygonGradeRenderer:function (sym, op) {
                var ranges = sym.ranges,
                    opacity = appConfig.global.opacity,
                    d = (opacity.max - opacity.min) / (sym.classify - 1),
                    color, symbol, i;

                var renderer = new ClassBreaksRenderer(false, function (g) {
                    return g.attributes[sym.field];
                });
                renderer.attributeField = sym.field;

                if (/^rgba/.test(sym.color)) {    // 透明色(兼容原来的设置)
                    color = new Color(sym.color);
                    symbol = symbolUtil.getFillSymbol(color);
                    renderer.addBreak(-Infinity, ranges[0][1], symbol);	// 添加第一个分级

                    for (i = 1; i < sym.classify - 1; ++i) {    // 添加中间分级
                        renderer.addBreak(ranges[i][0], ranges[i][1], symbol);
                    }
                } else if (/^#.{6}/.test(sym.color)) {    // 单色(兼容原来的设置)
                    color = utils.hex2rgb(sym.color);
                    symbol = symbolUtil.getFillSymbol(new Color(color.concat(opacity.min)));
                    renderer.addBreak(-Infinity, ranges[0][1], symbol);	// 添加第一个分级

                    for (i = 1; i < sym.classify - 1; ++i) {    // 添加中间分级
                        symbol = symbolUtil.getFillSymbol(new Color(color.concat(opacity.min + d * i)));
                        renderer.addBreak(ranges[i][0], ranges[i][1], symbol);
                    }
                    symbol = symbolUtil.getFillSymbol(new Color(color.concat(opacity.max)));
                } else {
                    var seq = colorBrewer["seq"][String(sym.classify)];	// TODO: 使用了全局变量gistech

                    if (!seq || !seq.hasOwnProperty(sym.color)) {
                        return null;
                    }
                    var colors = seq[sym.color].colors;
                    color = new Color(colors[0]);
                    color.a = op || 1;
                    symbol = symbolUtil.getFillSymbol(color);
                    renderer.addBreak(-Infinity, ranges[0][1], symbol);	// 添加第一个分级

                    for (i = 1; i < sym.classify - 1; ++i) {    // 添加中间分级
                        var color2 = new Color(colors[i]);
                        color2.a = op || 1;
                        symbol = symbolUtil.getFillSymbol(color2);
                        renderer.addBreak(ranges[i][0], ranges[i][1], symbol);
                    }
                    var color3 = new Color(colors[i]);
                    color3.a = op || 1;
                    symbol = symbolUtil.getFillSymbol(color3);
                }
                // 添加最后一个分级
                if (ranges[i][1] === 0) {
                    renderer.addBreak(ranges[i][0], 0, symbol);
                } else {
                    renderer.addBreak(ranges[i][0], Infinity, symbol);
                }
                return renderer;
            },

            /**
             * 获取要素极值
             * @param graphics
             * @param symbol
             */
            getExtreme:function (graphics, symbol) {
                if (!graphics) {
                    return null;
                }
                var values = [];

                array.forEach(graphics, function (g) {
                    values.push(g.attributes[symbol.field] || 0);
                });
                var max = Math.max.apply(Math, values),
                    min = Math.min.apply(Math, values);

                return { max:max, min:min, delta:(max - min) / symbol.classify };
            },

            /**
             * 获取等级符号范围
             * @param e
             * @param classify
             */
            getRanges:function (e, classify) {
                var prettify = utils.getPrettyNumber,
                    ranges = [
                        [e.min, prettify(e.min + e.delta)]
                    ],
                    i;

                for (i = 1; i < classify; ++i) {
                    ranges.push([
                        ranges[i - 1][1],
                        prettify(e.min + e.delta * (i + 1))
                    ]);
                }
                ranges[i - 1][1] = e.max;	// 最后一个范围的结束值是最大值(防止由于精度问题导致的累加误差)
                return ranges;
            },

            /**
             * 获取类型符号渲染器
             * @param opt
             */
            getTypesRenderer:function (opt) {
                var values = [];

                if (opt.maplayer.graphics.length > 0) {
                    values = this.getFieldValues(opt.maplayer.graphics, opt.symbol.field);
                    opt.symbol.values = values;
                } else {
                    values = opt.symbol.values;
                }

                if (!opt.remain || !opt.symbol.colors) {
                    opt.symbol.colors = utils.getGlobalColors(values.length);
                }
                if (opt.geometry === "point") {
                    return this.makePointTypesRenderer(opt.symbol);

                } else if (opt.geometry === "polygon") {
                    return this.makePolygonTypesRenderer(opt.symbol);
                }
                return null;
            },

            /**
             * 构造点类型符号渲染器
             * @param sym
             */
            makePointTypesRenderer:function (sym) {
                var i = 0,
                    symbol, renderer;

                var shadowSymbol = symbolUtil.getMarkerShadowSymbol(sym.size + 1);
                renderer = new UniqueValueRenderer(shadowSymbol, sym.field);

                array.forEach(sym.values, function (v) {
                    symbol = symbolUtil.getMarkerSymbol(sym.size, new Color(sym.colors[i++]));
                    renderer.addValue(v, symbol);
                }, this);

                return renderer;
            },

            /**
             * 构造面类型符号渲染器
             * @param sym
             */
            makePolygonTypesRenderer:function (sym) {
                var i = 0,
                    symbol, renderer;

                symbol = symbolUtil.getFillSymbol(sym.colors[0]);
                renderer = new UniqueValueRenderer(symbol, sym.field);

                array.forEach(sym.values, function (v) {
                    symbol = symbolUtil.getFillSymbol(new Color(sym.colors[i++]));
                    renderer.addValue(v, symbol);
                }, this);

                return renderer;
            },

            /**
             * 获取字段值列表
             * @param graphics
             * @param field
             */
            getFieldValues:function (graphics, field) {
                var values = [],
                    exists = {},
                    val;

                array.forEach(graphics, function (g) {
                    val = g.attributes[field] || "";

                    if (!exists.hasOwnProperty(val)) {
                        exists[val] = null;
                        values.push(val);
                    }
                });
                return values;
            }
        };
    });