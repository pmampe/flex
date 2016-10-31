var sysAdminIndexModule = (function($) {
  var initModule = function( container$ ) {
    console.log('sysAdminIndexModule.initModule: setting up js stuff ...');
  };

  return {initModule: initModule};
}(jQuery));

jQuery(document).ready(
  function() {
    sysAdminIndexModule.initModule(jQuery("body"));
  }
);

