import Ember from 'ember';

export default Ember.Route.extend({
    model: function() {
        var users = [],columns = [],key,arr = ['_id','__v'];
        return new Ember.RSVP.Promise(function(resolve, reject){
            Ember.$.ajax({
                url: 'http://localhost:8080/test-project/api/list/users',
                method: 'GET'
            }).then(function(res){
                users.clear();
                users.addObjects(res);
                for(key in users[0]){
                    if(arr.indexOf(key)==-1){
                        columns.push({name: key, show: true});
                    }
                } 
                resolve({
                    tableData: {
                        title: 'List of available Users',
                        columns: columns,
                        data: users
                    }
                });
            },function(err){
                reject(err);
            });
        });
    },
    actions: {
        removeUser: function(user) {
            var model = this.modelFor(this.routeName),users = model.tableData.data;
            users.removeObject(user);
        }
    }
});
//{{app-grid tableData=model.tableData remove="removeUser"}}
