// -----------------------------------------------------------------------------
// View details
// -----------------------------------------------------------------------------

function showAgencyUnitDetails( context ) {
  jQuery.post('getAgencyUnit.action', { id: context.id }, function( json ) {
    setInnerHTML('nameField', json.agencyUnit.name);
    setInnerHTML('programsField', json.agencyUnit.programs);
    setInnerHTML('mechanismUnitField', json.agencyUnit.mechanisms);
    setInnerHTML('codeField', json.agencyUnit.code);
    setInnerHTML('idField', json.agencyUnit.uid);
    setInnerHTML('userGroupField', json.agencyUnit.usergroup);
    setInnerHTML('categoryOptionGroupField', json.agencyUnit.categoryoptiongroup);
    setInnerHTML('donorUnitField', json.agencyUnit.donor);

    showDetails();
  });
}

// -----------------------------------------------------------------------------
// Remove
// -----------------------------------------------------------------------------

function removeAgencyUnit( context ) {
  removeItem(context.id, context.name, i18n_confirm_delete, 'removeAgencyUnit.action');
}
