/**
 * 通用方法
 * @author    yanggy
 * @version    0.1
 */

define("dojo/_base/declare dojo/_base/array esri/geometry/Polygon esri/geometry/Point esri/geometry/geodesicUtils esri/geometry/webMercatorUtils esri/units".split(" "),
    function (declare, array, Polygon, Point, geodesicUtils, webMercatorUtils, esriUnits) {

        return {

            /**
             * 获取字符串的字节长度
             * @param s
             *
             */
            getByteLength:function (s) {
                return (s || "").toString().trim().replace(/[^\x00-\xff]/g, "**").length;
            },

            /**
             * 截取指定长度字符串
             * @param s
             * @param len
             *
             */
            cutString:function (s, len) {
                var length = s.length,
                    byteLen = this.getByteLength(s),
                    r;

                if (len >= byteLen || len < 0) {
                    return s;
                }
                do {
                    --length;
                    r = s.slice(0, length);
                    byteLen = this.getByteLength(r);

                    if (byteLen <= len) {
                        return r;
                    }
                } while (length > 0);
            },

            /**
             * 截取字符串(返回截取之后的字符串和截取位置)
             * @param s
             * @param len
             *
             */
            truncateString:function (s, len) {
                var length = s.length,
                    byteLen = this.getByteLength(s),
                    r;

                if (len >= byteLen || len < 0) {
                    return { str:s, idx:length };
                }
                do {
                    --length;
                    r = s.slice(0, length);
                    byteLen = this.getByteLength(r);

                    if (byteLen <= len) {
                        return { str:r, idx:length };
                    }
                } while (length > 0);
            },

            /**
             * 截取指定长度字符串，超出长度补".."
             * @param s
             * @param len
             *
             */
            padString:function (s, len) {
                var result = s,
                    padStr = "..",
                    realLength = this.getByteLength(s);

                if (realLength > len) {
                    result = this.cutString(s, len - padStr.length) + padStr;
                }
                return result;
            },

            /**
             * 获取时间戳
             *
             */
            getTimeStamp:function () {
                var d = new Date(),
                    month = d.getMonth() + 1,
                    day = d.getDay(),
                    stamp = [];

                stamp.push(d.getFullYear());
                stamp.push(month > 9 ? month : "0" + month);
                stamp.push(day > 9 ? day : "0" + day);
                stamp.push(d.getHours());
                stamp.push(d.getMinutes());
                stamp.push(d.getSeconds());
                //stamp.push(d.getMilliseconds());
                return stamp.join("");
            },

            /**
             * 字符串是否以指定word开头
             * @param str
             * @param word
             *
             */
            startWith:function (str, word) {
                return new RegExp("^" + word).test(str);
            },

            /**
             * 字符串是否以指定word结尾
             * @param str
             *
             */
            endWith:function (str, word) {
                return new RegExp(word + "$").test(str);
            },

            /**
             * 处理货币形式的数据，exp: "29,568.12"
             * @param data 二维数组
             *
             */
            parseCsv:function (data) {
                var r = /^"{1}[\s\S]*"{1}$/, //双引号括起来的单词
                    cell;

                for (var i = 0; i < data.length; i++) {
                    for (var j = 0; j < data[i].length; j++) {
                        cell = data[i][j];

                        if (r.test(cell)) {
                            cell = cell.substr(1);
                            cell = cell.substr(0, cell.length - 1);
                            cell = cell.replace(/""/g, "\"");	//将转义的双引号""替换成"
                        }
                        var t2 = cell.replace(/,/g, "");

                        if (!isNaN(t2)) {    // 如果去掉引号后是数字，说明是货币格式，替换其中的引号
                            cell = cell.replace(/,/g, "");
                        }
                        data[i][j] = cell;
                    }
                }
                return data;
            },

            /**
             * 保留指定位数小数点
             * @param num
             * @param len
             *
             */
            getPrettyNumber:function (num, len) {
                if (typeof len === "number") {
                    if (len < 0) {
                        len = 3;
                    }
                } else {
                    len = 3;
                }
                var factor = Math.pow(10, len);
                return Math.round(num * factor) / factor;
            },

            /**
             * 将RGB颜色转化为16进制表示
             * @param rgb
             *
             */
            rgb2hex:function (rgb) {
                if (rgb instanceof Array && rgb.length > 2) {
                    var hex = "#",
                        c;

                    for (var i = 0; i < 3; ++i) {
                        if (typeof rgb[i] === "number" && rgb[i] >= 0 && rgb[i] <= 255) {
                            c = Math.round(rgb[i]);
                            hex += (c < 16) ? ("0" + c.toString(16)) : c.toString(16);
                        } else {
                            return null;
                        }
                    }
                    return hex;
                }
                return "";
            },

            /**
             * 将16进制颜色转化为RGB表示
             * @param hex
             *
             */
            hex2rgb:function (hex) {
                var hd = {
                        "0":0, "1":1, "2":2, "3":3, "4":4, "5":5, "6":6, "7":7, "8":8,
                        "9":9, "A":10, "B":11, "C":12, "D":13, "E":14, "F":15
                    },
                    p = /^#[0-9A-F]{3,6}$/,
                    result = [];

                if (typeof hex === "string" && p.test(hex.toUpperCase())) {
                    hex = hex.slice(1).toUpperCase();

                    if (hex.length === 3) {
                        result = [
                            hd[hex[0]] * 16 + hd[hex[0]],
                            hd[hex[1]] * 16 + hd[hex[1]],
                            hd[hex[2]] * 16 + hd[hex[2]]
                        ];
                    } else if (hex.length === 6) {
                        result = [
                            hd[hex[0]] * 16 + hd[hex[1]],
                            hd[hex[2]] * 16 + hd[hex[3]],
                            hd[hex[4]] * 16 + hd[hex[5]]
                        ];
                    }
                }
                return result;
            },

            /**
             * 根据颜色值数组构造RGBA颜色值
             * @param colorArr
             *
             */
            toRGBA:function (colorArr) {
                return "rgba(" + colorArr.join(",") + ")";
            },

            /**
             * 获取全局颜色
             * @param count
             *
             */
            getGlobalColors:function (count) {
                //var colors = "#ffa248 #33bfaf #e15ea2 #fd4949 #61c4ff #a0c12d #ff8ca4 #e7c400 #a861fc #2e6aff #f885ff #ff7800".split(" "),
                var colors = "#9994ce #feea89 #ff8989 #ed92cb #a8ed94 #ffcc8a #93d5ed #696a6c #feea89 #696389 #bee2f0 #ffcc8a #e38585".split(" "),
                    result = [];

                for (var i = 0, len = colors.length; i < count; ++i) {
                    result.push(colors[i % len]);
                }
                return result;
            },

            /**
             * 获取随机颜色值
             * @return {String}
             */
            getRandomColor:function () {
                return  '#' +
                    (function (color) {
                        return (color += '0123456789abcdef'[Math.floor(Math.random() * 16)])
                            && (color.length == 6) ? color : arguments.callee(color);
                    })('');
            },

            /**
             * 获取图片base64字符串
             * @param img
             * @param callback
             */
            getBase64:function (img, callback) {
                if (!img || !img.url) {
                    return null;
                }
                var image = new Image();
                image.src = img.url;
                image.crossOrigin = "Anonymous"; //TODO
                image.onload = function () {
                    var canvas = document.createElement("canvas");
                    canvas.width = image.width;
                    canvas.height = image.height;

                    var ctx = canvas.getContext("2d");
                    ctx.drawImage(image, 0, 0, image.width, image.height);
                    callback(canvas.toDataURL());
                };
            },

            /**
             * 数组是否包含指定的项
             * @param items
             * @param item
             */
            containItem:function (items, item) {
                if (!items instanceof Array) {
                    return false;
                }
                for (var i = 0, len = items.length; i < len; ++i) {
                    if (items[i] === item) {
                        return true;
                    }
                }
                return false;
            },

            /**
             * 移除数组元素
             * @param items
             * @param item
             */
            removeItem:function (items, item) {
                if (!items instanceof Array) {
                    return;
                }
                for (var i = 0, len = items.length; i < len; ++i) {
                    if (items[i] === item) {
                        items.splice(i, 1);
                        break;
                    }
                }
            },

            /**
             * 判断对象是否为{}或null
             * @param obj
             *
             */
            isEmpty:function (obj) {
                for (var name in obj) {
                    return false;
                }
                return true;
            },

            /**
             * 判断是否为数字
             * @param num
             * @return
             */
            isNumber:function (num) {
                return /^[+-]?\d+\.?\d*$/.test(num);
            },

            /**
             * 创建圆
             * @param point
             * @param distance
             * @param count
             *
             */
            createCircle:function (point, distance, count) {
                var smalllRaidan = 360 / count / 180 * Math.PI,
                    circlePoints = [],
                    radian, x, y, p;

                for (var i = 0; i < count; i++) {
                    radian = smalllRaidan * i;
                    x = point.x + Math.sin(radian) * distance;
                    y = point.y + Math.cos(radian) * distance;
                    p = new Point(x, y, point.spatialReference);
                    circlePoints.push(p);
                }
                circlePoints.push(circlePoints[0]);	// 闭合圆

                var circle = new Polygon(point.spatialReference);
                circle.addRing(circlePoints);
                return circle;
            },

            /**
             * 创建正方形
             * @param point
             * @param length
             */
            createSquare:function (point, length) {
                var centerX = point.x,
                    centerY = point.y,
                    minX = centerX - length / 2,
                    maxX = centerX + length / 2,
                    minY = centerY - length / 2,
                    maxY = centerY + length / 2;

                var ring = [];
                ring.push(new Point(minX, minY, point.spatialReference));
                ring.push(new Point(maxX, minY, point.spatialReference));
                ring.push(new Point(maxX, maxY, point.spatialReference));
                ring.push(new Point(minX, maxY, point.spatialReference));
                ring.push(new Point(minX, minY, point.spatialReference));

                var square = new Polygon(point.spatialReference);
                square.addRing(ring);

                return square;
            },

            /**
             * 计算区域面积
             * @param geometry
             *
             */
            caculateArea:function (polygon) {
                var geodesic = webMercatorUtils.webMercatorToGeographic(polygon);
                return geodesicUtils.geodesicAreas([geodesic], esriUnits.SQUARE_KILOMETERS)[0];
            },

            /**
             * GMT时间格式化成日期
             * @param date
             * @param type
             * @return {*}
             */
            dateToString:function (date) {
                var dtTmp = date;
                var year = "" + dtTmp.getFullYear();
                var month = "" + (dtTmp.getMonth() + 1);
                var day = "" + dtTmp.getDate();
                if (month.length == 1) {
                    month = "0" + month;
                }
                if (day.length == 1) {
                    day = "0" + day;
                }
                return year + "/" + month + "/" + day;
            },

            /**
             * 将数字格式化成货币的千分位格式
             * @param num
             * @return {*}
             */
            numToMoney:function (num) {
                num = num.toString();
                num = num.replace(/,/g, "");
                var re = /\d{1,3}(?=(\d{3})+$)/g;
                var n1 = num.replace(/^(\d+)((\.\d+)?)$/, function (s, s1, s2) {
                    return s1.replace(re, "$&,") + s2;
                });
                return n1;
            },

            /**
             * 获取占用空间信息
             * @param capacity
             */
            getSpace:function (capacity) {
                if (capacity < 1024) {
                    return Math.round(capacity * 10) / 10 + "KB";

                } else if (capacity < 1024 * 1024) {
                    return Math.round(capacity * 100 / 1024) / 100 + "MB";

                } else if (capacity < 1024 * 1024 * 1024) {
                    return Math.round(capacity * 100 / (1024 * 1024)) / 100 + "GB";

                } else if (capacity < 1024 * 1024 * 1024 * 1024) {
                    return Math.round(capacity * 100 / (1024 * 1024 * 1024)) / 100 + "TB";

                } else {
                    return capacity + "";	// Too Large, no need to calculate
                }
            },

            /**
             * 获取时间戳（YYYY/MM/DD HH:mm:SS）
             * @return string
             */
            getFormatDate:function () {
                var dtTmp = new Date();
                var year = "" + dtTmp.getFullYear();
                var month = "" + (dtTmp.getMonth() + 1);
                var day = "" + dtTmp.getDate();
                var hour = "" + dtTmp.getHours();
                var minute = "" + dtTmp.getMinutes();
                var second = "" + dtTmp.getSeconds();
                if (month.length == 1) {
                    month = "0" + month;
                }
                if (day.length == 1) {
                    day = "0" + day;
                }
                if (hour.length == 1) {
                    hour = "0" + hour;
                }
                if (minute.length == 1) {
                    minute = "0" + minute;
                }
                if (second.length == 1) {
                    second = "0" + second;
                }
                return year + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + second;
            },

            /**
             * 自然分组
             * @param nums
             * @param classify
             *
             */
            JenksNaturalBreaks:function (nums, classify) {
                var len = nums.length,
                    mat1 = [],
                    mat2 = [];

                nums = nums.sort(function (a, b) {
                    return a - b;
                });	// 升序排列
                classify = classify > len ? len : classify;

                for (var i = 0; i <= len; i++) {
                    var temp1 = [];
                    var temp2 = [];

                    for (var j = 0; j <= classify; j++) {
                        temp1.push(0);
                        temp2.push(0);
                    }
                    mat1.push(temp1);
                    mat2.push(temp2);
                }
                for (var i = 1; i <= classify; i++) {
                    mat1[1][i] = 1;
                    mat2[1][i] = 0;

                    for (var j = 2; j <= len; j++) {
                        mat2[j][i] = Infinity;
                    }
                }
                var v = 0.0;

                for (var l = 2; l <= len; l++) {
                    var s1 = 0.0;
                    var s2 = 0.0;
                    var w = 0.0;

                    for (var m = 1; m <= l; m++) {
                        var i3 = l - m + 1;
                        var val = parseFloat(nums[i3 - 1]);
                        s2 += val * val;
                        s1 += val;
                        w += 1;
                        v = s2 - (s1 * s1) / w;
                        var i4 = i3 - 1;

                        if (i4 != 0) {
                            for (var j = 2; j <= classify; j++) {
                                if (mat2[l][j] >= (v + mat2[i4][j - 1])) {
                                    mat1[l][j] = i3;
                                    mat2[l][j] = v + mat2[i4][j - 1];
                                }
                            }
                        }
                    }
                    mat1[l][1] = 1;
                    mat2[l][1] = v;
                }
                var k = len;
                var kclass = [];	// 区间值

                for (var j = 0; j <= classify; j++) {
                    kclass.push(0);
                }
                kclass[classify] = parseFloat(nums[len - 1]);
                kclass[0] = parseFloat(nums[0]);
                var c = classify;

                while (c >= 2) {
                    var id = parseInt((mat1[k][c]) - 2);

                    if (id < 0) {
                        id = 0;	// TODO id有时候为负数?
                    }
                    kclass[c - 1] = nums[id];
                    k = parseInt((mat1[k][c] - 1));
                    c -= 1;
                }
                return kclass;
            },

            /**
             * 构造指定长度的数据
             * @param len
             * @param value
             */
            makeArray:function (len, value) {
                var array = [];
                value = value || (value === 0 ? 0 : "");

                for (var i = 0; i < len; ++i) {
                    array.push(value);
                }
                return array;
            },

            /**
             * 获取url中的参数值
             * @param name
             * @return {*}
             */
            getUrlParam:function (name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return unescape(r[2]);
                return null;
            },

            /**
             * 根据字段名获取字段配置信息
             * @param name
             */
            getDicField:function (name, fields) {
                var info = null;
                for (var i = 0; i < fields.length; i++) {
                    if (fields[i].name === name) {
                        info = fields[i];
                        break;
                    }
                }
                return info;
            }
        };
    });