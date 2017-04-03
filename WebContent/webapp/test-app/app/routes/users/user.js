import Ember from 'ember';

export default Ember.Route.extend({
    model(params) {
        var columns = [],key,arr = ['_id','__v','userDetails'],data = [];
        this.set('userId',params.user_id);
        return new Ember.RSVP.Promise(function(resolve, reject){
            Ember.$.ajax({
                url: 'http://localhost:8080/test-project/api/list/notes?user='+params.user_id,
                method: 'GET'
            }).then(function(res){
                data = res.slice();
                if(data.length>0){
                    for(key in data[0]){
                        if(arr.indexOf(key)==-1){
                            columns.push({name: key, show: true});
                        }
                    }
                    resolve({
                        notes: {
                            title: 'List of Notes for ' + data[0].userDetails.name,
                            columns: columns,
                            data: data
                        }
                    });
                }
                else {
                    resolve({
                        notes: {
                            title: 'Notes not found for userId - ' + params.user_id,
                            columns: columns,
                            data: data
                        }
                    });
                }
            },function(err){
                reject(err);
            });
        });
    },
    actions: {
        addNewNote() {
            var notes = this.controller.get('model.notes');
            notes.data.addObject({text: '', user: this.get('userId'), time: new Date().toISOString().substring(0,10), editable: true});
            if(notes.columns.length==0){
                notes.columns.addObject('text');
                notes.columns.addObject('user');
                notes.columns.addObject('time');
            }
        },
        removeNote(note) {
            var notes = this.controller.get('model.notes');
            notes.data.removeObject(note);
        },
        saveNotes() {
            var notes = this.controller.get('model.notes.data'),userId = this.get('userId'),d = [],i,o;
            for(i in notes){
                if(!isNaN(i)){
                    o = notes[i];
                    delete o.userDetails;
                    d.push(o);
                }
            }
            Ember.$.ajax({
                url: 'http://localhost:8080/test-project/api/update/notes?user='+userId,
                method: 'POST',
                data: JSON.stringify(d,null),
                contentType: 'application/json'
            }).then(function(res){
                console.log(res);
            });
        }
    }
});
