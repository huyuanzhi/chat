require.config({shim:{underscore:{exports:"_"},backbone:{deps:["underscore","jquery"],exports:"Backbone"}},paths:{jquery:"../lib/jquery/jquery",underscore:"../lib/underscore/underscore",backbone:"../lib/backbone/backbone",text:"../lib/requirejs/text",templates:"templates"}});require(["my-app",],function(a){console.log(a);console.log($,"\n",_,"\n",Backbone)});