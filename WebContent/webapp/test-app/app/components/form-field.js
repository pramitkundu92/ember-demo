import Ember from 'ember';

export default Ember.Component.extend({
    type: 'text',
    actions: {
        change() {
            this.sendAction('change',document.getElementById(this.get('for')).value,this.get('for'));
        }
    }
});