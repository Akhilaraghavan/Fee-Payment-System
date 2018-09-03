$(document).ready(load);
/*
 * Once the DOM is ready for script execution,ie the DOM is loaded and parsed
 * the dom readyness can be detrmined by
 * document.addEventListener("DOMContentLoaded", function(event) { ...
 */
function load() {
	$('#studentlist').DataTable();
	
}

$(document).ready(function() {
	
	var feeTable = $('#fees').DataTable();
	
    $('#feeModal').on('show.bs.modal', function (e) {
    	var id = $('#feeId').val();
    	var url =  id == '' ? "/api/editFeeStructure" : "/api/editFeeStructure?id="+id;
        console.log(url)
    	$.ajax({
            type: "GET",
            contentype: "application/json",
            url: url
          }).done(function(data) {
              $('#admissionType').val(data.admissionType);
              $('#fromDate').val(data.fromDate);
              $('#toDate').val(data.toDate);
              $('#totalFees').val(data.totalFees);
              $('#feeId').val(data.id);
          });

        });//end on()
    
    $('#feeModal').on('hide.bs.modal', function (e) {
    	$('#feeId').val('');
    });
    
    $('#feeStructure').on('click', function() {
    	$.ajax({
            type: "GET",
            contentype: "application/json",
            url: "/api/getFees"
          }).done(function(data) {
        	  
          });

    });
    
    $('#saveAmountDue').on('click', function() {
    	var balance = $('#balance').val();
    	var studentId = $('#studentId').val();
    	alert(balance);
    	var data = {"studentId" : studentId, "balance":balance};
    	
    	$.ajax({
            type: "POST",
            url: "/api/saveAmountDue",
            data:data
          }).done(function(data) {
        	  
          });

    });
    
    
    	
});

var context = "";
function editStudent(id) {
	document.location.href = context + "/api/editStudent?id=" + id;
}

function editFeeStructure(id) {
	document.getElementById("feeId").value = id;
	$('#feeModal').modal();
}

function deleteFeeStructure(id) {
	document.location.href = context + "/api/deleteFeeStructure?id=" + id;
}

function studentPayment(id) {
	document.location.href = context + "/api/studentPayment?id=" + id;
}

function submitFeePayment() {
	$('#outstandingBalance').val($('#balance').val());
	$('#feepaymentform').submit();
}

function generateReceipt(id, rn, pd, ap, pm, cn) {
	document.location.href = context + "/api/generateReceipt?studentId=" + id + "&receiptNumber=" 
	+ rn + "&paymentDate=" + pd + "&amountPaid=" + ap + "&paymentMode=" + pm + "&chequeNumber=" + cn ;
}
