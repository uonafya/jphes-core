// -----------------------------------------------------------------------------
// View details
// -----------------------------------------------------------------------------

function showDonorUnitDetails( context ) {
  jQuery.post('getDonorUnit.action', { id: context.id }, function( json ) {
    setInnerHTML('nameField', json.donorUnit.name);
    setInnerHTML('programsField', json.donorUnit.programs);
    setInnerHTML('codeField', json.donorUnit.code);
    setInnerHTML('idField', json.donorUnit.uid);
    setInnerHTML('userGroupField', json.donorUnit.usergroup);
    setInnerHTML('categoryOptionGroupField', json.donorUnit.categoryoptiongroup);
    setInnerHTML('nationalUnitField', json.donorUnit.national);

    showDetails();
  });
}

// -----------------------------------------------------------------------------
// Remove
// -----------------------------------------------------------------------------

function removeDonorUnit( context ) {
  removeItem(context.id, context.name, i18n_confirm_delete, 'removeDonorUnit.action');
}

