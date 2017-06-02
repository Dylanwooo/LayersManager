/**
 * 提示信息
 * @author    yanggy
 * @version    0.1
 */

define("require dojo/_base/declare dojo/_base/lang".split(" "),
    function (require, declare, lang) {

        return {
            /**
             * 显示提示框
             * @param text
             * @param noshow
             * @param callback
             */
            showAlert:function (text, noshow, callback) {
                var params = {
                    text:text || "",
                    noshow:noshow || "",
                    callback:callback
                };
                require(["../widgets/Alert/Alert"], function (Alert) {
                    new Alert(params).startup();
                });
            },

            /**
             * 显示确认框
             * @param text
             * @param noshow
             * @param ok
             * @param cancel
             */
            showConfirm:function (text, noshow, ok, cancel, label, icon) {
                require(["../widgets/Confirm/Confirm"], lang.hitch(this, function (Confirm) {
                    var params = {
                        text:text || "",
                        noshow:noshow || "",
                        callback:lang.hitch(this, function (flag) {
                            if (typeof ok === "function") {
                                ok(flag);
                            }
                        }),
                        cancelCallback:lang.hitch(this, function (flag) {
                            if (typeof cancel === "function") {
                                cancel(flag);
                            }
                        })
                    };
                    if (label)
                        lang.mixin(params, {"label":label});
                    if (icon)
                        lang.mixin(params, {"icon":icon});
                    new Confirm(params).startup();
                }));
            },

            /**
             * 显示加载状态
             * @param text
             * @param req
             */
            showLoading:function (text, req) {
                require(["../widgets/Loading/Loading"], lang.hitch(this, function (Loading) {
                    this.loading = new Loading({
                        text:text || "",
                        req:req,
                        cancel:"取消"
                    });
                    this.loading.startup();
                }));
            },

            /**
             * 关闭加载状态
             * @param callback
             */
            hideLoading:function (callback) {
                if (this.loading) {
                    this.loading.close(callback);
                    this.loading = null;
                }
            },

            /**
             * 显示提示框
             * @param text
             * @param value
             * @param ok
             * @param cancel
             */
            showPrompt:function (text, value, ok, cancel) {
                require(["../widgets/Prompt/Prompt"], lang.hitch(this, function (Prompt) {
                    this.prompt = new Prompt({
                        text:text || "",
                        textvalue:value || "",
                        callback:lang.hitch(this, function (result) {
                            if (typeof ok === "function") {
                                return ok(result);
                            }
                            return true;
                        }),
                        cancelCallback:lang.hitch(this, function (result) {
                            if (typeof cancel === "function") {
                                return cancel(result);
                            }
                            return true;
                        })
                    });
                    this.prompt.startup();
                }));
            },

            /**
             * 关闭提示框
             * @param callback
             */
            hidePrompt:function (callback) {
                if (this.prompt) {
                    this.prompt.close(callback);
                    this.prompt = null;
                }
            },

            /**
             * 显示提示信息
             * @param tip
             * @param type
             */
            showTip:function (tip, type) {
                require(["../widgets/Tippet/Tippet"], function (Tippet) {
                    new Tippet({
                        tip:tip || "",
                        type:type || "info"
                    }).startup();
                });
            },

            /**
             * 显示新建进度条状态
             * @param width
             */
            showProgressBar:function (width, height) {
                require(["../widgets/ProgressBar/ProgressBar"], lang.hitch(this, function (ProgressBar) {
                    this.progressBar = new ProgressBar({
                        width:width || 400,
                        height:height || 30
                    });
                    this.progressBar.startup();
                }));
            },

            /**
             * 刷新进度条状态
             * @param barWidth
             */
            updateProgressBar:function (barWidth) {
                if (this.progressBar) {
                    this.progressBar.setProgress(barWidth);
                }
            },

            /**
             * 关闭进度条状态
             * @param callback
             */
            hideProgressBar:function (callback) {
                if (this.progressBar) {
                    this.progressBar.close(callback);
                    this.progressBar = null;
                }
            }
        };
    });