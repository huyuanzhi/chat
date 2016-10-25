var Login = function() {

    var handleLogin = function() {
        $('.login-form').validate({
            errorElement : 'span', // default input error message container
            errorClass : 'help-block', // default input error message class
            focusInvalid : false, // do not focus the last invalid input
            rules : {
                username : {
                    required : true
                },
                password : {
                    required : true
                },
                remember : {
                    required : false
                }
            },

            messages : {
                username : {
                    required : "用户名不能为空."
                },
                password : {
                    required : "密码不能为空."
                }
            },

            invalidHandler : function(event, validator) { // display error
                // alert on form
                // submit
                $('.alert-danger', $('.login-form')).show();
            },

            highlight : function(element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set
                // error
                // class
                // to
                // the
                // control
                // group
            },

            success : function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement : function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler : function(form) {
                var passwordInput = $('[name="password"]');
                passwordInput.val(sha256_digest(passwordInput.val()));
                form.submit();
            }
        });

        $('.login-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.login-form').validate().form()) {
                    $('.login-form').submit();
                }
                return false;
            }
        });
    }

    var handleForgetPassword = function() {
        $('.forget-form').validate({
            errorElement : 'span', // default input error message container
            errorClass : 'help-block', // default input error message class
            focusInvalid : false, // do not focus the last invalid input
            ignore : "",
            rules : {
                email : {
                    required : true,
                    email : true
                }
            },

            messages : {
                email : {
                    required : "请填写邮箱地址",
                    email:"非法的邮箱地址"
                }
            },

            invalidHandler : function(event, validator) { // display error
                // alert on form
                // submit

            },

            highlight : function(element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set
                // error
                // class
                // to
                // the
                // control
                // group
            },

            success : function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement : function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler : function(form) {
                form.submit();
            }
        });

        $('.forget-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.forget-form').validate().form()) {
                    $('.forget-form').submit();
                }
                return false;
            }
        });

        jQuery('#forget-password').click(function() {
            jQuery('.login-form').hide();
            jQuery('.forget-form').show();
        });

        jQuery('#back-btn').click(function() {
            jQuery('.login-form').show();
            jQuery('.forget-form').hide();
        });

    }

    var handleRegister = function() {

        function format(state) {
            if (!state.id)
                return state.text; // optgroup
            /*return "<img class='flag' src='assets/img/flags/"
                    + state.id.toLowerCase() + ".png'/>&nbsp;&nbsp;"
                    + state.text;*/

            return "<i class='fa fa-"+state.id+"'></i>&nbsp;&nbsp;"
                + state.text;
        }

        $("#select2_sample4")
                .select2(
                        {
                            placeholder : '<i class="fa fa-male"></i>&nbsp;性别',
                            allowClear : true,
                            formatResult : format,
                            formatSelection : format,
                            escapeMarkup : function(m) {
                                return m;
                            }
                        });

        $('#select2_sample4').change(function() {
            $('.register-form').validate().element($(this)); // revalidate
            // the chosen
            // dropdown
            // value and
            // show error or
            // success
            // message for
            // the input
        });

        $('.register-form').validate({
            errorElement : 'span', // default input error message container
            errorClass : 'help-block', // default input error message class
            focusInvalid : false, // do not focus the last invalid input
            ignore : "",
            rules : {
                email : {
                    required : true,
                    email : true,
                    remote : {
                        url :"/user/exist",
                        type : "post",
                        data : {
                            email : function(){return $('#email').val()}
                        }
                    }
                },
                username : {
                    required : true
                },
                password : {
                    required : true
                },
                rpassword : {
                    equalTo : "#register_password"
                },

                tnc : {
                    required : true
                }
            },

            messages : {

                email:{
                    required : "请输入邮箱地址",
                    email : "请输入一个有效的邮箱地址",
                    remote :jQuery.format("此邮箱已被注册！")
                },
                username:{
                    required : "请输入昵称"
                },
                password : {
                    required : "请输入密码"
                },
                rpassword : {
                    required : "请再次输入密码",
                    equalTo : "两次密码不一致"
                },
                tnc : {
                    required : "请勾选服务条款."
                }
            },

            invalidHandler : function(event, validator) { // display error
                // alert on form
                // submit

            },

            highlight : function(element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set
                // error
                // class
                // to
                // the
                // control
                // group
            },

            success : function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement : function(error, element) {
                if (element.attr("name") == "tnc") { // insert checkbox
                    // errors after the
                    // container
                    error.insertAfter($('#register_tnc_error'));
                } else if (element.closest('.input-icon').size() === 1) {
                    error.insertAfter(element.closest('.input-icon'));
                } else {
                    error.insertAfter(element);
                }
            },

            submitHandler : function(form) {
                var passwordInput = $('[name="password"]');
                passwordInput.val(sha256_digest(passwordInput.val()));
                form.submit();
            }
        });

        $('.register-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.register-form').validate().form()) {
                    $('.register-form').submit();
                }
                return false;
            }
        });

        jQuery('#register-btn').click(function() {
            jQuery('.login-form').hide();
            jQuery('.register-form').show();
        });

        jQuery('#register-back-btn').click(function() {
            jQuery('.login-form').show();
            jQuery('.register-form').hide();
        });
    }

    return {
        // main function to initiate the module
        init : function() {
            console.log(12);
            handleLogin();
            handleForgetPassword();
            handleRegister();

            $.backstretch([ "assets/img/bg/1.jpg", "assets/img/bg/2.jpg",
                    "assets/img/bg/3.jpg", "assets/img/bg/4.jpg" ], {
                fade : 1000,
                duration : 8000
            });
        }

    };

}();