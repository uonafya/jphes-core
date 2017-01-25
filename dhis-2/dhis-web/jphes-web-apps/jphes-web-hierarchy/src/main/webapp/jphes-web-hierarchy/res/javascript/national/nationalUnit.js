// -----------------------------------------------------------------------------
// View details
// -----------------------------------------------------------------------------

function showNationalUnitDetails( context ) {
  jQuery.post('getNationalUnit.action', { id: context.id }, function( json ) {
    setInnerHTML('nameField', json.nationalUnit.name);
    setInnerHTML('programsField', json.nationalUnit.programs);
    setInnerHTML('donorsField', json.nationalUnit.donors);
    setInnerHTML('codeField', json.nationalUnit.code);
    setInnerHTML('idField', json.nationalUnit.uid);
    setInnerHTML('userGroupField', json.nationalUnit.usergroup);
    setInnerHTML('donorGroupSetField', json.nationalUnit.donorgroupset);
    setInnerHTML('agencyGroupSetField', json.nationalUnit.agencygroupset);
    setInnerHTML('mechanismCategoryField', json.nationalUnit.mechanismcategory);
    setInnerHTML('mechanismComboField', json.nationalUnit.mechanismcombo);
    setInnerHTML('hrefField', '../api/nationalUnits/'+json.nationalUnit.uid);
    setInnerHTML('createDateField', json.nationalUnit.created);

    showDetails();
  });
}

// -----------------------------------------------------------------------------
// Remove
// -----------------------------------------------------------------------------

function removeNationalUnit( context ) {
  removeItem(context.id, context.name, i18n_confirm_delete, 'removeNationalUnit.action');
}

// -----------------------------------------------------------------------------
// View Programs
// -----------------------------------------------------------------------------

function viewNationalPrograms( context ) {

  location.href = 'viewNationalPrograms.action?id=' + context.id;

}