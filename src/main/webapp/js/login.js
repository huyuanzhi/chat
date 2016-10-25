/**
 * Created by Huyuanzhi on 2016/9/30.
 */
var Login = {
    init:function(){
        this.initNode();
        this.initTabToggle();
        this.addEvent();
    },
    initNode:function(){
        this.$dialog=$('#mainDialog');
        this.$btnLogin=$('#btnLogin');
        this.$btnRegister=$('#btnRegister');
        this.$goRegister=$('#goRegister');
        this.$goLogin=$('#btnLogin');
    },

    initTabToggle:function(){
        $('.register').hide();
    },
    addEvent:function(){
        this.$goRegister.on('click',function(){
            $('.register').show();
            $('.login').hide();
        });
        this.$goLogin.on('click',function(){
            $('.login').show();
            $('.register').hide();
        });
    }
};
