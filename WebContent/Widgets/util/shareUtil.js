/**
 * User: yuhao
 * Date: 15-5-20
 * Time: 下午4:02
 * 共享工具。可以导出地图、新浪微博分享
 */

define("require dojo/_base/declare dojo/_base/lang dojo/_base/array dojo/io-query dojo/query ./ajaxUtil ./infoUtil ./utils".split(" "),
    function (require, declare, lang, array, ioQuery, query, ajaxUtil, infoUtil, utils) {

        return {
            prefix:"http://usergp.geoq.cn/rest/rest/",

            printUrl:"http://tools.arcgisonline.cn/arcgis/rest/services/Utilities/PrintingTools/GPServer/Export Web Map Task/execute",

            imageType:"png",

            template:"A4 Landscape",

            //exportRoot:"#visual-area",

            /**
             * 导出地图
             * @param params 导出地图的配图参数
             * @param callback
             */
            exportMap:function (params, callback) {
                var that = this,
                    map = params.map,
                    title = params.title,
                    username = params.username,
                    options = this._getExportOptions(map, title, username, this.template),
                    outputSize = options.exportOptions.outputSize,
                    mapJSON = null;

                infoUtil.showLoading("导出地图...");

                try {
                    mapJSON = JSON.stringify(options);
                } catch (e) {
                    infoUtil.hideLoading();
                    infoUtil.showTip("导出地图失败 :-(", "danger");
                    return;
                }
                ajaxUtil.esriReq(this.printUrl, {
                    Web_Map_as_JSON:mapJSON,
                    Layout_Template:this.template,
                    Format:"png32",
                    f:"json"
                }, true).then(function (resopnse) {
                        infoUtil.hideLoading();

                        if (resopnse.results[0].value) {
                            callback(resopnse.results[0].value.url, outputSize[0], outputSize[1], that.imageType);
                        } else {
                            callback(null, outputSize[0], outputSize[1], that.imageType);
                        }
                    }, function (error) {
                        infoUtil.hideLoading();
                        infoUtil.showTip("导出地图失败 :-(", "danger");
                    });

                /*require(["../lib/Html2Canvas"], lang.hitch(this, function (Html2Canvas) {
                 Html2Canvas($(this.exportRoot), {
                 onrendered:function (canvas) {
                 infoUtil.hideLoading();
                 callback(canvas.toDataURL("image/jpeg", 0.9), canvas.width, canvas.height, "jpg");
                 },
                 proxy:"getImage.do"
                 });
                 }));*/
            },

            /**
             * 获取地图导出选项
             * @param map
             * @param template
             * @return
             */
            _getExportOptions:function (map, title, username, template) {
                var options = {
                    "mapOptions":{
                        "showAttribution":false,
                        "extent":this._getMapExtent(map),
                        "spatialReference":{ "wkid":map.spatialReference.wkid },
                        "scale":map.getScale()
                    },
                    "operationalLayers":[],
                    "exportOptions":{
                        "outputSize":this._getTemplateSize(map, template),
                        "dpi":96
                    },
                    "layoutOptions":{
                        "titleText":title,
                        "authorText":"Print by " + username,
                        "copyrightText":"© gisuni",
                        "scaleBarOptions":{
                            "metricUnit":"kilometers",
                            "metricLabel":"km",
                            "nonMetricUnit":"miles",
                            "nonMetricLabel":"mi"
                        }
                    }
                };
                var layers = map.getLayersVisibleAtScale(map.getScale());

                layers = array.filter(layers, function (layer) {
                    return !layer.ignore;	// 忽略不需要导出的图层
                });
                array.forEach(layers, function (layer) {
                    if (layer.visible) {    // 只导出可见图层
                        if (layer.url && layer.tileInfo) {    // TiledLayer
                            options.operationalLayers.push(this._getTiledLayerOption(layer));

                        } else if (layer.graphics) {    // GraphicsLayer
                            options.operationalLayers.push(this._getGraphicsLayerOption(layer));

                        } else if (layer._img && layer._img.src) {    // HeatmapLayer
                            options.operationalLayers.push(this._getImageLayerOption(map, layer._img.src));
                        }
                    }
                }, this);

                return options;
            },

            /**
             * 获取瓦片图层选项
             * @param layer
             * @return
             */
            _getTiledLayerOption:function (layer) {
                return {
                    "minScale":layer.minScale,
                    "maxScale":layer.maxScale,
                    "url":layer.url,
                    "opacity":layer.opacity
                };
            },

            /**
             * 获取GraphicsLayer选项
             * @param layer
             * @return
             */
            _getGraphicsLayerOption:function (layer) {
                var layerOption = lang.clone(layer.layerOption),
                    layer0 = layerOption.featureCollection.layers[0],
                    texts = [], // 文本Graphic
                    gs = [];	// 普通Graphic

                var graphics = array.filter(layer.graphics, function (g) {
                    return g.visible;	// 只导出可见Graphic
                });

                for (var i = 0; i < graphics.length; ++i) {
                    var g = graphics[i];

                    if (g.symbol && g.symbol.type === "textsymbol") {
                        texts.push(g);
                    } else {
                        gs.push(g);
                    }
                }
                if (texts.length > 0) {
                    // 追加文本配置
                    layerOption.featureCollection.layers.push({
                        "layerDefinition":{
                            "name":"",
                            "geometryType":"esriGeometryPoint"
                        },
                        "featureSet":{
                            "geometryType":"esriGeometryPoint",
                            "features":[]
                        }
                    });
                    var layer1 = layerOption.featureCollection.layers[1],
                        labelField = layer.labelField;

                    layer1.featureSet.features = array.map(texts, function (t) {
                        var textJson = t.toJson();
                        // BUG: 补充如下属性，否则文本颜色值设置无效
                        lang.mixin(textJson.symbol.font, { "style":"normal", "weight":"normal", "family":"simsun" });

                        if (labelField) {
                            // BUG: LabelLayer每个Graphic中Symbol的text属性值是错的(全部都一样)，使用之前存储在attributes中的值
                            textJson.symbol.text = t.attributes[labelField];
                        } else {
                            textJson.symbol.text = textJson.symbol.text + "";	// BUG: 数字不转成字符串显示不出来
                        }
                        delete textJson.attributes;	// 删除属性字段(因为缺少字段配置，不删除的话会报错)
                        delete textJson.infoTemplate;	// InfoTemplate中引用了GraphicLayer，调用toJson方法的时候会出现循环引用错误
                        return textJson;
                    });
                }
                layer0.featureSet.features = array.map(gs, function (g) {
                    var feature = g.toJson();
                    delete feature.infoTemplate;	// InfoTemplate中引用了GraphicLayer，调用toJson方法的时候会出现循环引用错误
                    return feature;
                });

                if (layer.renderer) {
                    var renderer = this._rendererToJson(layer.renderer, layer0.layerDefinition.geometryType);
                    layer0.layerDefinition.drawingInfo.renderer = renderer;
                }
                layerOption.opacity = layer.opacity;
                return layerOption;
            },

            /**
             * 获取图片图层选项
             * @param map
             * @param src
             * @return
             */
            _getImageLayerOption:function (map, src) {
                return {
                    "type":"image",
                    "extent":this._getMapExtent(map),
                    "imageData":src.slice(src.indexOf(",") + 1)
                };
            },

            /**
             * 获取地图范围
             * @param map
             * @return
             */
            _getMapExtent:function (map) {
                return {
                    "xmin":map.extent.xmin,
                    "ymin":map.extent.ymin,
                    "xmax":map.extent.xmax,
                    "ymax":map.extent.ymax,
                    "spatialReference":{ "wkid":map.spatialReference.wkid }
                };
            },

            /**
             * 获取模板大小
             * @param map
             * @param template
             */
            _getTemplateSize:function (map, template) {
                switch (template) {
                    case "A4 Landscape":
                        var w = map.width,
                            h = map.height,
                            a4w = 1123,
                            a4h = 794;

                        if (w >= a4w) {
                            if (h >= a4h) {
                                return [a4w, a4h];
                            } else {
                                return [Math.round(a4w * h / a4h), h];
                            }
                        } else {
                            if (h >= a4h) {
                                return [w, Math.round(w * a4h / a4w)];
                            } else {
                                if (w * a4h >= h * a4w) {
                                    return [Math.round(h * a4w / a4h), h];
                                } else {
                                    return [w, Math.round(w * a4h / a4w)];
                                }
                            }
                        }
                    case "MAP_ONLY":
                    default:
                        return [map.width, map.height];
                }
            },

            /**
             * 将渲染器转换为Json
             * @param renderer
             * @param geotype
             * @return
             */
            _rendererToJson:function (renderer, geotype) {
                if (!renderer) {
                    return null;
                }
                var declass = renderer.declaredClass,
                    result = renderer.toJson();

                switch (declass) {
                    case "esri.renderer.UniqueValueRenderer":
                        if (geotype === "esriGeometryPolygon") {
                            delete result.defaultSymbol;	// BUG: 面类型符号渲染不删除该属性的话会导出失败
                        }
                        break;
                    case "esri.renderer.ClassBreaksRenderer":
                        // BUG: ClassBreaksRenderer.toJson之后部分分段颜色值丢失，手动补上
                        var hex2rgb = utils.hex2rgb;

                        for (var i = 0; i < result.classBreakInfos.length; ++i) {
                            var color = renderer.infos[i].symbol.color;

                            if (color instanceof Array) {
                                result.classBreakInfos[i].symbol.color = [color.r, color.g, color.b, 255];
                            } else if (/#.{6}/.test(color)) {
                                result.classBreakInfos[i].symbol.color = hex2rgb(color).concat(255);
                            }
                        }
                        break;
                    default:
                        break;
                }
                return result;
            }
        };
    });