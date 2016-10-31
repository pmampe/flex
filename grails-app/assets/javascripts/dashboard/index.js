var dashboardIndexModule = (function($) {
  var initModule = function( container$ ) {
    console.log('dashboardIndexModule.initModule: setting up js stuff ...');
  };

  return {initModule: initModule};
}(jQuery));

jQuery(document).ready(
  function() {
    dashboardIndexModule.initModule(jQuery("body"));
  }
);
