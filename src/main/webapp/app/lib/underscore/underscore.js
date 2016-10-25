(function(){var w=this;var k=w._;var D={};var C=Array.prototype,f=Object.prototype,r=Function.prototype;var H=C.push,o=C.slice,y=C.concat,d=f.toString,j=f.hasOwnProperty;var L=C.forEach,q=C.map,E=C.reduce,c=C.reduceRight,b=C.filter,B=C.every,p=C.some,n=C.indexOf,l=C.lastIndexOf,u=Array.isArray,e=Object.keys,F=r.bind;var M=function(N){if(N instanceof M){return N}if(!(this instanceof M)){return new M(N)}this._wrapped=N};if(typeof exports!=="undefined"){if(typeof module!=="undefined"&&module.exports){exports=module.exports=M}exports._=M}else{w._=M}M.VERSION="1.6.0";var I=M.each=M.forEach=function(S,P,O){if(S==null){return S}if(L&&S.forEach===L){S.forEach(P,O)}else{if(S.length===+S.length){for(var N=0,R=S.length;N<R;N++){if(P.call(O,S[N],N,S)===D){return}}}else{var Q=M.keys(S);for(var N=0,R=Q.length;N<R;N++){if(P.call(O,S[Q[N]],Q[N],S)===D){return}}}}return S};M.map=M.collect=function(Q,P,O){var N=[];if(Q==null){return N}if(q&&Q.map===q){return Q.map(P,O)}I(Q,function(T,R,S){N.push(P.call(O,T,R,S))});return N};var g="Reduce of empty array with no initial value";M.reduce=M.foldl=M.inject=function(R,Q,N,P){var O=arguments.length>2;if(R==null){R=[]}if(E&&R.reduce===E){if(P){Q=M.bind(Q,P)}return O?R.reduce(Q,N):R.reduce(Q)}I(R,function(U,S,T){if(!O){N=U;O=true}else{N=Q.call(P,N,U,S,T)}});if(!O){throw new TypeError(g)}return N};M.reduceRight=M.foldr=function(T,Q,N,P){var O=arguments.length>2;if(T==null){T=[]}if(c&&T.reduceRight===c){if(P){Q=M.bind(Q,P)}return O?T.reduceRight(Q,N):T.reduceRight(Q)}var S=T.length;if(S!==+S){var R=M.keys(T);S=R.length}I(T,function(W,U,V){U=R?R[--S]:--S;if(!O){N=T[U];O=true}else{N=Q.call(P,N,T[U],U,V)}});if(!O){throw new TypeError(g)}return N};M.find=M.detect=function(Q,O,P){var N;A(Q,function(T,R,S){if(O.call(P,T,R,S)){N=T;return true}});return N};M.filter=M.select=function(Q,N,P){var O=[];if(Q==null){return O}if(b&&Q.filter===b){return Q.filter(N,P)}I(Q,function(T,R,S){if(N.call(P,T,R,S)){O.push(T)}});return O};M.reject=function(P,N,O){return M.filter(P,function(S,Q,R){return !N.call(O,S,Q,R)},O)};M.every=M.all=function(Q,O,P){O||(O=M.identity);var N=true;if(Q==null){return N}if(B&&Q.every===B){return Q.every(O,P)}I(Q,function(T,R,S){if(!(N=N&&O.call(P,T,R,S))){return D}});return !!N};var A=M.some=M.any=function(Q,O,P){O||(O=M.identity);var N=false;if(Q==null){return N}if(p&&Q.some===p){return Q.some(O,P)}I(Q,function(T,R,S){if(N||(N=O.call(P,T,R,S))){return D}});return !!N};M.contains=M.include=function(O,N){if(O==null){return false}if(n&&O.indexOf===n){return O.indexOf(N)!=-1}return A(O,function(P){return P===N})};M.invoke=function(P,Q){var N=o.call(arguments,2);var O=M.isFunction(Q);return M.map(P,function(R){return(O?Q:R[Q]).apply(R,N)})};M.pluck=function(O,N){return M.map(O,M.property(N))};M.where=function(O,N){return M.filter(O,M.matches(N))};M.findWhere=function(O,N){return M.find(O,M.matches(N))};M.max=function(R,P,O){if(!P&&M.isArray(R)&&R[0]===+R[0]&&R.length<65535){return Math.max.apply(Math,R)}var N=-Infinity,Q=-Infinity;I(R,function(V,S,U){var T=P?P.call(O,V,S,U):V;if(T>Q){N=V;Q=T}});return N};M.min=function(R,P,O){if(!P&&M.isArray(R)&&R[0]===+R[0]&&R.length<65535){return Math.min.apply(Math,R)}var N=Infinity,Q=Infinity;I(R,function(V,S,U){var T=P?P.call(O,V,S,U):V;if(T<Q){N=V;Q=T}});return N};M.shuffle=function(Q){var P;var O=0;var N=[];I(Q,function(R){P=M.random(O++);N[O-1]=N[P];N[P]=R});return N};M.sample=function(O,P,N){if(P==null||N){if(O.length!==+O.length){O=M.values(O)}return O[M.random(O.length-1)]}return M.shuffle(O).slice(0,Math.max(0,P))};var a=function(N){if(N==null){return M.identity}if(M.isFunction(N)){return N}return M.property(N)};M.sortBy=function(P,O,N){O=a(O);return M.pluck(M.map(P,function(S,Q,R){return{value:S,index:Q,criteria:O.call(N,S,Q,R)}}).sort(function(T,S){var R=T.criteria;var Q=S.criteria;if(R!==Q){if(R>Q||R===void 0){return 1}if(R<Q||Q===void 0){return -1}}return T.index-S.index}),"value")};var t=function(N){return function(R,Q,P){var O={};Q=a(Q);I(R,function(U,S){var T=Q.call(P,U,S,R);N(O,T,U)});return O}};M.groupBy=t(function(N,O,P){M.has(N,O)?N[O].push(P):N[O]=[P]});M.indexBy=t(function(N,O,P){N[O]=P});M.countBy=t(function(N,O){M.has(N,O)?N[O]++:N[O]=1});M.sortedIndex=function(U,T,Q,P){Q=a(Q);var S=Q.call(P,T);var N=0,R=U.length;while(N<R){var O=(N+R)>>>1;Q.call(P,U[O])<S?N=O+1:R=O}return N};M.toArray=function(N){if(!N){return[]}if(M.isArray(N)){return o.call(N)}if(N.length===+N.length){return M.map(N,M.identity)}return M.values(N)};M.size=function(N){if(N==null){return 0}return(N.length===+N.length)?N.length:M.keys(N).length};M.first=M.head=M.take=function(P,O,N){if(P==null){return void 0}if((O==null)||N){return P[0]}if(O<0){return[]}return o.call(P,0,O)};M.initial=function(P,O,N){return o.call(P,0,P.length-((O==null)||N?1:O))};M.last=function(P,O,N){if(P==null){return void 0}if((O==null)||N){return P[P.length-1]}return o.call(P,Math.max(P.length-O,0))};M.rest=M.tail=M.drop=function(P,O,N){return o.call(P,(O==null)||N?1:O)};M.compact=function(N){return M.filter(N,M.identity)};var x=function(O,P,N){if(P&&M.every(O,M.isArray)){return y.apply(N,O)}I(O,function(Q){if(M.isArray(Q)||M.isArguments(Q)){P?H.apply(N,Q):x(Q,P,N)}else{N.push(Q)}});return N};M.flatten=function(O,N){return x(O,N,[])};M.without=function(N){return M.difference(N,o.call(arguments,1))};M.partition=function(Q,N){var P=[],O=[];I(Q,function(R){(N(R)?P:O).push(R)});return[P,O]};M.uniq=M.unique=function(T,S,R,Q){if(M.isFunction(S)){Q=R;R=S;S=false}var O=R?M.map(T,R,Q):T;var P=[];var N=[];I(O,function(V,U){if(S?(!U||N[N.length-1]!==V):!M.contains(N,V)){N.push(V);P.push(T[U])}});return P};M.union=function(){return M.uniq(M.flatten(arguments,true))};M.intersection=function(O){var N=o.call(arguments,1);return M.filter(M.uniq(O),function(P){return M.every(N,function(Q){return M.contains(Q,P)})})};M.difference=function(O){var N=y.apply(C,o.call(arguments,1));return M.filter(O,function(P){return !M.contains(N,P)})};M.zip=function(){var P=M.max(M.pluck(arguments,"length").concat(0));var O=new Array(P);for(var N=0;N<P;N++){O[N]=M.pluck(arguments,""+N)}return O};M.object=function(R,O){if(R==null){return{}}var N={};for(var P=0,Q=R.length;P<Q;P++){if(O){N[R[P]]=O[P]}else{N[R[P][0]]=R[P][1]}}return N};M.indexOf=function(R,P,Q){if(R==null){return -1}var N=0,O=R.length;if(Q){if(typeof Q=="number"){N=(Q<0?Math.max(0,O+Q):Q)}else{N=M.sortedIndex(R,P);return R[N]===P?N:-1}}if(n&&R.indexOf===n){return R.indexOf(P,Q)}for(;N<O;N++){if(R[N]===P){return N}}return -1};M.lastIndexOf=function(R,P,Q){if(R==null){return -1}var N=Q!=null;if(l&&R.lastIndexOf===l){return N?R.lastIndexOf(P,Q):R.lastIndexOf(P)}var O=(N?Q:R.length);while(O--){if(R[O]===P){return O}}return -1};M.range=function(S,P,R){if(arguments.length<=1){P=S||0;S=0}R=arguments[2]||1;var Q=Math.max(Math.ceil((P-S)/R),0);var N=0;var O=new Array(Q);while(N<Q){O[N++]=S;S+=R}return O};var G=function(){};M.bind=function(Q,O){var N,P;if(F&&Q.bind===F){return F.apply(Q,o.call(arguments,1))}if(!M.isFunction(Q)){throw new TypeError}N=o.call(arguments,2);return P=function(){if(!(this instanceof P)){return Q.apply(O,N.concat(o.call(arguments)))}G.prototype=Q.prototype;var S=new G;G.prototype=null;var R=Q.apply(S,N.concat(o.call(arguments)));if(Object(R)===R){return R}return S}};M.partial=function(N){var O=o.call(arguments,1);return function(){var P=0;var Q=O.slice();for(var R=0,S=Q.length;R<S;R++){if(Q[R]===M){Q[R]=arguments[P++]}}while(P<arguments.length){Q.push(arguments[P++])}return N.apply(this,Q)}};M.bindAll=function(O){var N=o.call(arguments,1);if(N.length===0){throw new Error("bindAll must be passed function names")}I(N,function(P){O[P]=M.bind(O[P],O)});return O};M.memoize=function(P,O){var N={};O||(O=M.identity);return function(){var Q=O.apply(this,arguments);return M.has(N,Q)?N[Q]:(N[Q]=P.apply(this,arguments))}};M.delay=function(O,P){var N=o.call(arguments,2);return setTimeout(function(){return O.apply(null,N)},P)};M.defer=function(N){return M.delay.apply(M,[N,1].concat(o.call(arguments,1)))};M.throttle=function(O,Q,U){var N,S,V;var T=null;var R=0;U||(U={});var P=function(){R=U.leading===false?0:M.now();T=null;V=O.apply(N,S);N=S=null};return function(){var W=M.now();if(!R&&U.leading===false){R=W}var X=Q-(W-R);N=this;S=arguments;if(X<=0){clearTimeout(T);T=null;R=W;V=O.apply(N,S);N=S=null}else{if(!T&&U.trailing!==false){T=setTimeout(P,X)}}return V}};M.debounce=function(P,R,O){var U,T,N,S,V;var Q=function(){var W=M.now()-S;if(W<R){U=setTimeout(Q,R-W)}else{U=null;if(!O){V=P.apply(N,T);N=T=null}}};return function(){N=this;T=arguments;S=M.now();var W=O&&!U;if(!U){U=setTimeout(Q,R)}if(W){V=P.apply(N,T);N=T=null}return V}};M.once=function(P){var N=false,O;return function(){if(N){return O}N=true;O=P.apply(this,arguments);P=null;return O}};M.wrap=function(N,O){return M.partial(O,N)};M.compose=function(){var N=arguments;return function(){var O=arguments;for(var P=N.length-1;P>=0;P--){O=[N[P].apply(this,O)]}return O[0]}};M.after=function(O,N){return function(){if(--O<1){return N.apply(this,arguments)}}};M.keys=function(P){if(!M.isObject(P)){return[]}if(e){return e(P)}var O=[];for(var N in P){if(M.has(P,N)){O.push(N)}}return O};M.values=function(R){var Q=M.keys(R);var P=Q.length;var N=new Array(P);for(var O=0;O<P;O++){N[O]=R[Q[O]]}return N};M.pairs=function(R){var P=M.keys(R);var O=P.length;var Q=new Array(O);for(var N=0;N<O;N++){Q[N]=[P[N],R[P[N]]]}return Q};M.invert=function(R){var N={};var Q=M.keys(R);for(var O=0,P=Q.length;O<P;O++){N[R[Q[O]]]=Q[O]}return N};M.functions=M.methods=function(P){var O=[];for(var N in P){if(M.isFunction(P[N])){O.push(N)}}return O.sort()};M.extend=function(N){I(o.call(arguments,1),function(O){if(O){for(var P in O){N[P]=O[P]}}});return N};M.pick=function(O){var P={};var N=y.apply(C,o.call(arguments,1));I(N,function(Q){if(Q in O){P[Q]=O[Q]}});return P};M.omit=function(P){var Q={};var O=y.apply(C,o.call(arguments,1));for(var N in P){if(!M.contains(O,N)){Q[N]=P[N]}}return Q};M.defaults=function(N){I(o.call(arguments,1),function(O){if(O){for(var P in O){if(N[P]===void 0){N[P]=O[P]}}}});return N};M.clone=function(N){if(!M.isObject(N)){return N}return M.isArray(N)?N.slice():M.extend({},N)};M.tap=function(O,N){N(O);return O};var J=function(U,T,O,P){if(U===T){return U!==0||1/U==1/T}if(U==null||T==null){return U===T}if(U instanceof M){U=U._wrapped}if(T instanceof M){T=T._wrapped}var R=d.call(U);if(R!=d.call(T)){return false}switch(R){case"[object String]":return U==String(T);case"[object Number]":return U!=+U?T!=+T:(U==0?1/U==1/T:U==+T);case"[object Date]":case"[object Boolean]":return +U==+T;case"[object RegExp]":return U.source==T.source&&U.global==T.global&&U.multiline==T.multiline&&U.ignoreCase==T.ignoreCase}if(typeof U!="object"||typeof T!="object"){return false}var N=O.length;while(N--){if(O[N]==U){return P[N]==T}}var S=U.constructor,Q=T.constructor;if(S!==Q&&!(M.isFunction(S)&&(S instanceof S)&&M.isFunction(Q)&&(Q instanceof Q))&&("constructor" in U&&"constructor" in T)){return false}O.push(U);P.push(T);var X=0,W=true;if(R=="[object Array]"){X=U.length;W=X==T.length;if(W){while(X--){if(!(W=J(U[X],T[X],O,P))){break}}}}else{for(var V in U){if(M.has(U,V)){X++;if(!(W=M.has(T,V)&&J(U[V],T[V],O,P))){break}}}if(W){for(V in T){if(M.has(T,V)&&!(X--)){break}}W=!X}}O.pop();P.pop();return W};M.isEqual=function(O,N){return J(O,N,[],[])};M.isEmpty=function(O){if(O==null){return true}if(M.isArray(O)||M.isString(O)){return O.length===0}for(var N in O){if(M.has(O,N)){return false}}return true};M.isElement=function(N){return !!(N&&N.nodeType===1)};M.isArray=u||function(N){return d.call(N)=="[object Array]"};M.isObject=function(N){return N===Object(N)};I(["Arguments","Function","String","Number","Date","RegExp"],function(N){M["is"+N]=function(O){return d.call(O)=="[object "+N+"]"}});if(!M.isArguments(arguments)){M.isArguments=function(N){return !!(N&&M.has(N,"callee"))}}if(typeof(/./)!=="function"){M.isFunction=function(N){return typeof N==="function"}}M.isFinite=function(N){return isFinite(N)&&!isNaN(parseFloat(N))};M.isNaN=function(N){return M.isNumber(N)&&N!=+N};M.isBoolean=function(N){return N===true||N===false||d.call(N)=="[object Boolean]"};M.isNull=function(N){return N===null};M.isUndefined=function(N){return N===void 0};M.has=function(O,N){return j.call(O,N)};M.noConflict=function(){w._=k;return this};M.identity=function(N){return N};M.constant=function(N){return function(){return N}};M.property=function(N){return function(O){return O[N]}};M.matches=function(N){return function(P){if(P===N){return true}for(var O in N){if(N[O]!==P[O]){return false}}return true}};M.times=function(R,Q,P){var N=Array(Math.max(0,R));for(var O=0;O<R;O++){N[O]=Q.call(P,O)}return N};M.random=function(O,N){if(N==null){N=O;O=0}return O+Math.floor(Math.random()*(N-O+1))};M.now=Date.now||function(){return new Date().getTime()};var m={escape:{"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#x27;"}};m.unescape=M.invert(m.escape);var K={escape:new RegExp("["+M.keys(m.escape).join("")+"]","g"),unescape:new RegExp("("+M.keys(m.unescape).join("|")+")","g")};M.each(["escape","unescape"],function(N){M[N]=function(O){if(O==null){return""}return(""+O).replace(K[N],function(P){return m[N][P]})}});M.result=function(N,P){if(N==null){return void 0}var O=N[P];return M.isFunction(O)?O.call(N):O};M.mixin=function(N){I(M.functions(N),function(O){var P=M[O]=N[O];M.prototype[O]=function(){var Q=[this._wrapped];H.apply(Q,arguments);return s.call(this,P.apply(M,Q))}})};var z=0;M.uniqueId=function(N){var O=++z+"";return N?N+O:O};M.templateSettings={evaluate:/<%([\s\S]+?)%>/g,interpolate:/<%=([\s\S]+?)%>/g,escape:/<%-([\s\S]+?)%>/g};var v=/(.)^/;var h={"'":"'","\\":"\\","\r":"r","\n":"n","\t":"t","\u2028":"u2028","\u2029":"u2029"};var i=/\\|'|\r|\n|\t|\u2028|\u2029/g;M.template=function(V,Q,P){var O;P=M.defaults({},P,M.templateSettings);var R=new RegExp([(P.escape||v).source,(P.interpolate||v).source,(P.evaluate||v).source].join("|")+"|$","g");var S=0;var N="__p+='";V.replace(R,function(X,Y,W,aa,Z){N+=V.slice(S,Z).replace(i,function(ab){return"\\"+h[ab]});if(Y){N+="'+\n((__t=("+Y+"))==null?'':_.escape(__t))+\n'"}if(W){N+="'+\n((__t=("+W+"))==null?'':__t)+\n'"}if(aa){N+="';\n"+aa+"\n__p+='"}S=Z+X.length;return X});N+="';\n";if(!P.variable){N="with(obj||{}){\n"+N+"}\n"}N="var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};\n"+N+"return __p;\n";try{O=new Function(P.variable||"obj","_",N)}catch(T){T.source=N;throw T}if(Q){return O(Q,M)}var U=function(W){return O.call(this,W,M)};U.source="function("+(P.variable||"obj")+"){\n"+N+"}";return U};M.chain=function(N){return M(N).chain()};var s=function(N){return this._chain?M(N).chain():N};M.mixin(M);I(["pop","push","reverse","shift","sort","splice","unshift"],function(N){var O=C[N];M.prototype[N]=function(){var P=this._wrapped;O.apply(P,arguments);if((N=="shift"||N=="splice")&&P.length===0){delete P[0]}return s.call(this,P)}});I(["concat","join","slice"],function(N){var O=C[N];M.prototype[N]=function(){return s.call(this,O.apply(this._wrapped,arguments))}});M.extend(M.prototype,{chain:function(){this._chain=true;return this},value:function(){return this._wrapped}});if(typeof define==="function"&&define.amd){define("underscore",[],function(){return M})}}).call(this);