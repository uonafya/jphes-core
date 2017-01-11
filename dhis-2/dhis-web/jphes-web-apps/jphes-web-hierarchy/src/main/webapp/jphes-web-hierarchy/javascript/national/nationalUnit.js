// -----------------------------------------------------------------------------
// View details
// -----------------------------------------------------------------------------

function showNationalUnitDetails( context ) {
  jQuery.post('getNationalUnit.action', { id: context.id }, function( json ) {
    setInnerHTML('nameField', json.nationalUnit.name);
    setInnerHTML('programsField', json.nationalUnit.programs);
    setInnerHTML('codeField', json.nationalUnit.code);
    setInnerHTML('idField', json.nationalUnit.uid);
    setInnerHTML('userGroupField', json.nationalUnit.usergroup);
    setInnerHTML('mechanismCategoryField', json.nationalUnit.mechanismcategory);
    setInnerHTML('mechanismComboField', json.nationalUnit.mechanismcombo);

    showDetails();
  });
}

// -----------------------------------------------------------------------------
// Remove
// -----------------------------------------------------------------------------

function removeNationalUnit( context ) {
  removeItem(context.id, context.name, i18n_confirm_delete, 'removeNationalUnit.action');
}

