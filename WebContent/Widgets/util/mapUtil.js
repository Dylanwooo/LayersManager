/**
 * User: yuhao
 * Date: 14-12-3
 * Time: 下午5:05
 * 地图常用操作类
 */

define("require dojo/_base/declare dojo/_base/lang dojo/_base/array dojo/io-query esri/geometry/Multipoint esri/geometry/Point esri/SpatialReference".split(" "),
    function (require, declare, lang, array, ioQuery, Multipoint, Point, SpatialReference) {

        return {

            /**
             * 缩放到图层
             * @param layer
             */
            zoomToLayer:function (map, layer) {
                if (layer instanceof esri.layers.GraphicsLayer) {
                    var graphics = layer.graphics;
                    this.zoomToGraphics(map, graphics);
                }
            },

            /**
             * 缩放到图形
             * @param graphics
             */
            zoomToGraphics:function (map, graphics) {
                if (!map || !graphics) {
                    return;
                }
                var multipoints = new Multipoint(new SpatialReference({ wkid:102100 })),
                    extent = null,
                    allpoint = true, // 全部是点
                    total = 0, // 可见图形数
                    type;

                array.forEach(graphics, function (g) {
                    if (g.visible && g.geometry) {
                        type = g.geometry.type;

                        if (type === "polygon" || type === "polyline" || type === "extent") {
                            if (extent == null) {
                                extent = g.geometry.getExtent();
                            } else {
                                extent = extent.union(g.geometry.getExtent());
                            }
                            allpoint = false;
                        } else if (type === "point") {
                            multipoints.addPoint(g.geometry);
                        }
                        ++total;
                    }
                });

                if (allpoint && total === 1) {    // 只有一个点时单独处理
                    map.centerAndZoom(multipoints.getPoint(0), map.getMaxZoom() - 1);
                    return;
                }
                if (extent != null) {
                    if (multipoints.points.length > 0) {
                        extent = extent.union(multipoints.getExtent());
                    }
                } else if (multipoints.points.length > 0) {
                    extent = multipoints.getExtent();
                }
                if (extent) {
                    map.setExtent(extent.getExtent().expand(2), false);
                }       
            },

            /**
             * 创建聚合图层(Data Server)
             * @param map
             * @param params
             * @param name
             */
            createClusterLayer:function (map, params, name) {
                if (!params || !name || !map) {
                    return null;
                }
                var layer = null;
                require(["../map/layer/ClusterLayer"], function (ClusterLayer) {
                    layer = new ClusterLayer({
                        map:map,
                        data:params.data,
                        title:name,
                        opacity:1 - params.opacity / 100
                        //TODO:应只传入需要的属性
                        //appConfig:appConfig
                    });
                });
                return layer;
            },

            /**
             * 创建聚合图层
             * @param map
             * @param params
             * @param name
             */
            createCityClusterLayer:function (map, params, name, provinceField, cityField) {
                if (!params || !name || !map) {
                    return null;
                }
                var layer = null;
                require(["../map/layer/CityClusterLayer"], function (CityClusterLayer) {
                    layer = new CityClusterLayer({
                        map:map,
                        data:params.features,
                        field:params.field,
                        type:params.type,
                        title:name,
                        opacity:1 - params.opacity / 100,
                        provinceField:provinceField,
                        cityField:cityField
                        //TODO:应只传入需要的属性
                        //appConfig:appConfig
                    });
                });
                return layer;
            },

            /**
             * 创建图表图层
             * @param map
             * @param params
             */
            createChartLayer:function (map, params) {
                if (!params || !map) {
                    return null;
                }

                var layer = null;
                require(["../map/layer/ChartLayer"], function (ChartLayer) {
                    layer = new ChartLayer({
                        map:map,
                        features:params.features,
                        fields:params.fields,
                        colors:params.colors,
                        showFields:params.showFields,
                        type:params.type,
                        title:params.title,
                        method:params.method,
                        classify:params.classify,
                        opacity:1 - params.opacity / 100
                        //TODO:应只传入需要的属性
                        //appConfig:appConfig
                    });
                });
                return layer;
            },

            /**
             * 创建区划图层
             * @param map
             * @param params
             * @param name
             */
            createDistrictLayer:function (map, params, name) {
                if (!params || !name || !map) {
                    return null;
                }

                var layer = null;
                require(["../map/layer/DistrictLayer"], function (DistrictLayer) {
                    layer = new DistrictLayer({
                        map:map,
                        data:params.data,
                        layer:params.layer,
                        edit:params.edit,
                        title:name
                        //TODO:应只传入需要的属性
                        //appConfig:appConfig
                    });
                });
                return layer;
            },

            /**
             * 创建热度图层
             * @param map
             * @param params
             */
            createHeatmapLayer:function (map, params) {
                if (!map || !params) {
                    return null;
                }

                var layer = null;
                require(["../map/layer/HeatmapLayer"], function (HeatmapLayer) {
                    layer = new HeatmapLayer({
                        map:map,
                        domNodeId:params.domId,
                        opacity:1 - params.opacity / 100
                    });
                });
                return layer;
            },

            /**
             * get radius of grid
             * @param level
             */
            getGridRadius:function (level) {
                if (level < 11) {
                    return 8000;
                }
                if (level < 12) {
                    return 4000;
                }
                if (level < 13) {
                    return 2000;
                }
                if (level < 15) {
                    return 1000;
                }
                if (level < 16) {
                    return 500;
                }
                return 250;
            },

            /**
             * 获取某一位置上重合的要素
             * @param pos
             * @param features
             * @return {Array}
             */
            getFeaturesOnSamePos:function (pos, features) {
                var geoType = pos.type,
                    result = [];

                if (geoType === "point") {
                    result = array.filter(features, function (f) {
                        return f.geometry.x === pos.x && f.geometry.y === pos.y;
                    });
                }
                else if (geoType === "polygon") {
                    var geometry,
                        rings,
                        paths;

                    for (var i = 0; i < features.length; i++) {
                        geometry = features[i].geometry;
                        rings = geometry.rings;
                        if (pos.rings.length != rings.length)
                            continue;

                        var pathSame = true;
                        for (var j = 0; j < rings.length; j++) {
                            paths = rings[j];
                            if (pos.rings[j].length != paths.length) {
                                pathSame = false;
                                break;
                            }
                        }
                        if (!pathSame) {
                            continue;
                        }
                        else {
                            if (JSON.stringify(pos.geometry.toJson()) === JSON.stringify(geometry.toJson())) {
                                result.push(features[i]);
                            }
                        }
                    }
                }
                return result;
            },

            /**
             * 获取polygon的中心点
             * @param geometry
             * @return {Point}
             */
            getCenter:function (geometry) {
                var sumx = 0,
                    sumy = 0,
                    points = geometry.rings[0];
                for (var i = 0; i < points.length; i++) {
                    sumx += points[i][0];
                    sumy += points[i][1];
                }
                var position = new Point(sumx / points.length, sumy / points.length, geometry.spatialReference);
                return position;
            },

            /**
             * 获取图层JSON对象
             * @param layer
             */
            graphicsLayer2Json:function (layer) {
                if (!layer || !layer instanceof esri.layers.GraphicsLayer) {
                    return null;
                }
                var result = {};

                result.graphics = array.map(layer.graphics, function (g) {
                    return g.toJson();
                });
                if (layer.renderer) {
                    result.renderer = layer.renderer.toJson();
                }
                return result;
            }
        };
    });