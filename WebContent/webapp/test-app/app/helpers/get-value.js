import Ember from 'ember';

export function getValue(params/*, hash*/) {
    var obj = params[0], field = params[1];
    if(obj.hasOwnProperty(field)){
        return obj[field];
    }
    else return undefined;
}

export default Ember.Helper.helper(getValue);
