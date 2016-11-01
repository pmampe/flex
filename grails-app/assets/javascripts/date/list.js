var dateListModule = (function($) {
  var initModule = function( container$ ) {
    console.log('dateListModule.initModule: setting up js stuff ...');
  };

  return {initModule: initModule};
}(jQuery));

jQuery(document).ready(
  function() {
    dateListModule.initModule(jQuery("body"));
  }
);
