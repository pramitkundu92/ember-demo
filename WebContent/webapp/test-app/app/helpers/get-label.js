import Ember from 'ember';

export function getLabel(params/*, hash*/) {
  return 'Enter ' + params[0];
}

export default Ember.Helper.helper(getLabel);
