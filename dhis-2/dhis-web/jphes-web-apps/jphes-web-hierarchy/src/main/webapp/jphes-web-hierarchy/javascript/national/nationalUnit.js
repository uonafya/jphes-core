// -----------------------------------------------------------------------------
// View details
// -----------------------------------------------------------------------------

function showNationalUnitDetails( context ) {
  jQuery.post('getNationalUnit.action', { id: context.id }, function( json ) {
    setInnerHTML('nameField', json.nationalUnit.name);
    setInnerHTML('createdField', json.nationalUnit.created);
    setInnerHTML('lastUpdatedField', json.nationalUnit.lastUpdated);
    setInnerHTML('codeField', json.nationalUnit.code);
    setInnerHTML('idField', json.nationalUnit.uid);

    showDetails();
  });
}

// -----------------------------------------------------------------------------
// Remove
// -----------------------------------------------------------------------------

function removeNationalUnit( context ) {
  removeItem(context.id, context.name, i18n_confirm_delete, 'removeNationalUnit.action');
}

