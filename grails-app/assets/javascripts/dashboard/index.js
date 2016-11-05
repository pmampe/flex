var dashboardIndexModule = (function($) {
  var initModule = function( container$ ) {
    $("input#starttime").unbind('change');
    $("input#starttime").on('change', function(event) {
      event.preventDefault();
      var timeregex = /^\d{1,2}:\d{2}([ap]m)?$/;
      var starttime = $("input#starttime").val();
      if(starttime.value != '' && !starttime.match(timeregex)) {
        $("div#feedbackmessage").html('<strong>Malformed starttime</strong>');
        $("input#starttime").focus();
        return false;
      }
      var timeparts = starttime.split(":");
      if(timeparts[0]>23) {
        $("div#feedbackmessage").html('<strong>Hour must be between 0-23</strong>');
        $("input#starttime").focus();
        return false;
      }
      if(timeparts[1]>59) {
        $("div#feedbackmessage").html('<strong>Minute must be between 0-59</strong>');
        $("input#starttime").focus();
        return false;
      }
      $("div#feedbackmessage").html('');
      return true;
    });
    
    $("input#endtime").unbind('change');
    $("input#endtime").on('change', function(event) {
      event.preventDefault();
      var timeregex = /^\d{1,2}:\d{2}$/;
      var endtime = $("input#endtime").val();
      if(endtime.value != '' && !endtime.match(timeregex)) {
        $("div#feedbackmessage").html('<strong>Malformed endtime</strong>');
        $("input#endtime").focus();
        return false;
      }
      var timeparts = endtime.split(":");
      if(timeparts[0]>23) {
        $("div#feedbackmessage").html('<strong>Hour must be between 0-23</strong>');
        $("input#endtime").focus();
        return false;
      }
      if(timeparts[1]>59) {
        $("div#feedbackmessage").html('<strong>Minute must be between 0-59</strong>');
        $("input#endtime").focus();
        return false;
      }
      var starttime=$("input#starttime").val()
      if(endtime<=starttime) {
        $("div#feedbackmessage").html('<strong>Endtime should be after starttime</strong>');
        $("input#endtime").focus();
        return false;
      }
      $("div#feedbackmessage").html('');
      return true;
    });
    
    $("input#lunchlength").unbind('change');
    $("input#lunchlength").on('change', function(event) {
      event.preventDefault();
      var timeregex = /^\d{1,3}$/;
      var lunchlength = $("input#lunchlength").val();
      if(lunchlength.value != '' && !lunchlength.match(timeregex)) {
        $("div#feedbackmessage").html('<strong>Malformed lunchlength</strong>');
        $("input#lunchlength").focus();
        return false;
      }
      if(lunchlength>180) {
        $("div#feedbackmessage").html('<strong>More than 3 hours lunch?</strong>');
      }
      return true;
    });
    $('input#absentAllDay').on('click', function(event) {
      var absentAllDay = $('input#absentAllDay').is(':checked');
      if(absentAllDay) {
        $("input#lunchlength").val("0");
        $("input#endtime").val("00:00")
        $("input#starttime").val("00:00")
        $("input#lunchlength").attr('disabled', true);
        $("input#endtime").attr('disabled', true);
        $("input#starttime").attr('disabled', true);
      } else {
        $("input#lunchlength").val(30);
        $("input#lunchlength").attr('disabled', false);
        $("input#endtime").attr('disabled', false);
        $("input#starttime").attr('disabled', false);
      }
    });
    var absentAllDay = $('input#absentAllDay').is(':checked');
    if(absentAllDay) {
      $("input#lunchlength").val("0");
      $("input#endtime").val("00:00")
      $("input#starttime").val("00:00")
      $("input#lunchlength").attr('disabled', true);
      $("input#endtime").attr('disabled', true);
      $("input#starttime").attr('disabled', true);
    }    
  };

  return {initModule: initModule};
}(jQuery));

jQuery(document).ready(
  function() {
    dashboardIndexModule.initModule(jQuery("body"));
  }
);
