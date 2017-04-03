import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('index',{path: '/'});
  this.route('home');
  this.route('users',{path: '/users'},function(){
      this.route('user',{path: '/:user_id'});
  });
});

export default Router;
