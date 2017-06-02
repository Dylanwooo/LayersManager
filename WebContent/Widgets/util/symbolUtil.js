/**
 * 样式
 * @author    yanggy
 * @version    0.1
 */

define("dojo/_base/declare dojo/_base/Color esri/symbols/Font esri/symbols/SimpleFillSymbol esri/symbols/SimpleLineSymbol esri/symbols/SimpleMarkerSymbol esri/symbols/TextSymbol esri/symbols/PictureMarkerSymbol".split(" "),
    function (declare, Color, Font, SimpleFillSymbol, SimpleLineSymbol, SimpleMarkerSymbol,TextSymbol,PictureMarkerSymbol) {

        return {
            /**
             * 获取点样式
             * @param size
             * @param color
             * @param borderColor
             * @param borderWidth
             * @param style
             * @return
             */
            getMarkerSymbol:function (size, color, borderColor, borderWidth, style) {
                size = size || 12;
                color = color || new Color([51, 153, 255, 1]);
                borderColor = borderColor || new Color([255, 255, 255]);
                borderWidth = borderWidth || 1;
                style = style || SimpleMarkerSymbol.STYLE_CIRCLE;
                return new SimpleMarkerSymbol(style, size, new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, borderColor, borderWidth), color);
            },
            
            getLineSymbol:function(color,width){
            	return new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, color, width); 
            },

            /**
             * 获取点阴影样式
             * @param size
             * @param style
             */
            getMarkerShadowSymbol:function (size, style) {
                size = size || 13;
                style = style || SimpleMarkerSymbol.STYLE_CIRCLE;
                return new SimpleMarkerSymbol(style, size, null, new Color("#555555")).setOffset(2, -2);
            },

            /**
             * 获取填充样式
             * @param color
             * @param outlineColor
             * @param outlineWidth
             * @param style
             * @return
             */
            getFillSymbol:function (color, outlineColor, outlineWidth, style) {
                color = color || new Color([51, 153, 255, 0.3]);
                outlineColor = outlineColor || new Color([255, 255, 255]);
                outlineWidth = outlineWidth || 1;
                style = style || SimpleFillSymbol.STYLE_SOLID;
                return new SimpleFillSymbol(style, new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, outlineColor, outlineWidth), color);
            },

            /**
             * 按颜色名获取填充样式
             * @parma colorName
             * @return
             */
            getColorfulFillSymbol:function (colorName) {
                var color = new Color([97, 196, 255, 0.3]),
                    outlineColor = new Color([225, 225, 225]);

                switch (colorName) {
                    case "red":
                        color = new Color([253, 73, 73, 0.3]);
                        outlineColor = new Color([225, 225, 225]);
                        break;
                    case "green":
                        color = new Color([51, 191, 175, 0.3]);
                        outlineColor = new Color([225, 225, 225]);
                        break;
                    case "purple":
                        color = new Color([133, 48, 181, 0.5]);
                        outlineColor = new Color([225, 225, 225]);
                        break;
                    case "blue":
                    default:
                        break;
                }
                return this.getFillSymbol(color, outlineColor);
            },

            /**
             * 获取默认填充样式
             * @return
             */
            getDefaultFillSymbol:function () {
                return this.getColorfulFillSymbol("blue");
            },

            /**
             * 获取文本符号
             * @param text
             * @param color
             * @param offset
             * @param fontSize
             * @return
             */
            getTextSymbol:function (text, color, offset, fontSize) {
                fontSize = fontSize || "12px";
                color = color || new Color([255, 255, 255]);
                var symbol = new TextSymbol(text, new Font({ size:fontSize }), color);

                if (offset && offset.hasOwnProperty("x") && offset.hasOwnProperty("y")) {
                    symbol.setOffset(offset.x, offset.y);
                }
                return symbol;
            },
            
            getPictureSymbol:function (url,width,height,offset) {
            	var pictureSymbol = new PictureMarkerSymbol(url,width,height);
            	pictureSymbol.setColor(new Color([255,255,0,0]));
            	if (offset && offset.hasOwnProperty("x") && offset.hasOwnProperty("y")) {
            		pictureSymbol.setOffset(offset.x, offset.y);
                }
                return pictureSymbol;
            },
            
            //说明：1:图片都放在VehicleTree的images目录下
            //    2:图片命名规则是vtype_status
            /*getVehicleSymbol:function(status,vtype){
            	return this.getPictureSymbol(,54,42);
            }*/
        };
    });