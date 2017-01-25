// -----------------------------------------------------------------------------
// View details
// -----------------------------------------------------------------------------

function showMechanismUnitDetails( context ) {
  jQuery.post('getMechanismUnit.action', { id: context.id }, function( json ) {
    setInnerHTML('nameField', json.mechanismUnit.name);
    setInnerHTML('partnerField', json.mechanismUnit.partner);
    setInnerHTML('programsField', json.mechanismUnit.programs);
    setInnerHTML('orgunitsField', json.mechanismUnit.orgunits);
    setInnerHTML('codeField', json.mechanismUnit.code);
    setInnerHTML('idField', json.mechanismUnit.uid);
    setInnerHTML('userGroupField', json.mechanismUnit.usergroup);
    setInnerHTML('startDateField', json.mechanismUnit.startdate);
    setInnerHTML('endDateField', json.mechanismUnit.enddate);
    setInnerHTML('createDateField', json.mechanismUnit.created);
    setInnerHTML('hrefField', '../api/mechanismUnits/'+json.mechanismUnit.uid);

    showDetails();
  });
}

// -----------------------------------------------------------------------------
// Remove
// -----------------------------------------------------------------------------

function removeMechanismUnit( context ) {
  removeItem(context.id, context.name, i18n_confirm_delete, 'removeMechanismUnit.action');
}


// -----------------------------------------------------------------------------
// View Organisation Units for a MechanismUnit
// -----------------------------------------------------------------------------

function viewOrganistionUnits( context ) {

  location.href = 'viewMechanismOrgUnits.action?uid=' + context.uid;

}

// -----------------------------------------------------------------------------
// View Programs
// -----------------------------------------------------------------------------

function viewMechanismPrograms( context ) {

  location.href = 'viewMechanismPrograms.action?id=' + context.id;

}

// -----------------------------------------------------------------------------
// filter MechanismUnits by AgencyUnit
// -----------------------------------------------------------------------------

function filterAgencyUnits() {

  var agencyUnit = $('#agencyUnits').val();

  var url = 'allMechanismUnit.action?';

  url += agencyUnit ? 'agencyUnit=' + agencyUnit + '&' : '';

  window.location.href = url;
}
