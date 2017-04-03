import Ember from 'ember';

export default Ember.Component.extend({
    init() {
        this._super(...arguments);
        this.colWidth = 'cell col-md-1';
        var cols = this.get('tableData.columns'),dataset = this.get('tableData.data');
        dataset.forEach((d)=>{
            d.editable = false;
        });           
        this.set('colWidth','cell col-md-'+Math.floor(12/(cols.length+1)));
        this.set('editObject',Ember.Object.create());
        if(cols !== undefined && cols.length>0 && cols[0].constructor == String){
            cols = cols.map((c)=>{ return {name: c, show: true}; });
            this.set('tableData.columns',cols);
        }        
    },
    didUpdateAttrs() {
        this._super(...arguments);
        var cols = this.get('tableData.columns');
        this.set('colWidth','cell col-md-1');
        this.set('colWidth','cell col-md-'+Math.floor(12/(cols.length+1)));
        if(cols !== undefined && cols.length>0 && cols[0].constructor == String){
            cols = cols.map((c)=>{ return {name: c, show: true}; });
            this.set('tableData.columns',cols);
        }
    },
    actions: {
        remove(param) {
            this.sendAction('remove',param);
        },
        add() {
            this.sendAction('add');
        },
        save(row,index){
            var data = this.get('tableData.data'),cols = this.get('tableData.columns'),
                editObject = this.get('editObject'),obj = {editable: false};
            cols.forEach((c)=>{
                obj[c.name] = editObject.get(c.name);    
            });
            data.replace(index,1,obj);
        },
        userInputRecvd(value,fieldName){
            var editObject = this.get('editObject');
            editObject.set(fieldName,value);
        }
    }
});
