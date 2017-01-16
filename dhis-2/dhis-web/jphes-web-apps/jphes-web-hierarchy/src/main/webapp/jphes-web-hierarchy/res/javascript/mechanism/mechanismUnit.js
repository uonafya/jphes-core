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
    setInnerHTML('categoryOptionField', json.mechanismUnit.categoryoption);
    setInnerHTML('agencyUnitField', json.mechanismUnit.agency);
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

