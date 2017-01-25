// -----------------------------------------------------------------------------
// View details
// -----------------------------------------------------------------------------

function showProgramDetails( context ) {
  jQuery.post('getProgram.action', { id: context.id }, function( json ) {
    setInnerHTML('nameField', json.program.name);
    setInnerHTML('shortnameField', json.program.shortName);
    setInnerHTML('descriptionField', json.program.description);
    setInnerHTML('codeField', json.program.code);
    setInnerHTML('uidField', json.program.uid);

    showDetails();
  });
}

// -----------------------------------------------------------------------------
// Remove Program
// -----------------------------------------------------------------------------

function removeProgram( context ) {
  removeItem(context.id, context.name, i18n_confirm_delete, 'removeProgram.action');
}

// -----------------------------------------------------------------------------
// View Indicators
// -----------------------------------------------------------------------------

function viewProgramIndicators( context ) {

  location.href = 'viewProgramIndicators.action?id=' + context.id;

}

// -----------------------------------------------------------------------------
// View DataElements
// -----------------------------------------------------------------------------

function viewProgramDataElements( context ) {

  location.href = 'viewProgramDataElements.action?id=' + context.id;

}

