define(["underscore","backbone"],function(b,c){var a=c.Model.extend({idAttribute:"uuid",urlRoot:"path/to/json/examples",defaults:{name:"<no name>"}});return a});