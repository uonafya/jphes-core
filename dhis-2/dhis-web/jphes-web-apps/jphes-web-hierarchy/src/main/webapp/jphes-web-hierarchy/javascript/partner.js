// -----------------------------------------------------------------------------
// View details
// -----------------------------------------------------------------------------

function showPartnerDetails( context ) {
  jQuery.post('getPartner.action', { id: context.id }, function( json ) {
    setInnerHTML('nameField', json.partner.name);
    setInnerHTML('createdField', json.partner.created);
    setInnerHTML('lastUpdatedField', json.partner.lastUpdated);
    setInnerHTML('codeField', json.partner.code);
    setInnerHTML('idField', json.partner.uid);

    showDetails();
  });
}

// -----------------------------------------------------------------------------
// Remove Partner
// -----------------------------------------------------------------------------

function removePartner( context ) {
  removeItem(context.id, context.name, i18n_confirm_delete, 'removeRole.action');
}

