// -----------------------------------------------------------------------------
// View details
// -----------------------------------------------------------------------------

function showProgramDetails( context ) {
  jQuery.post('getProgram.action', { id: context.id }, function( json ) {
    setInnerHTML('nameField', json.program.name);
    setInnerHTML('createdField', json.program.created);
    setInnerHTML('lastUpdatedField', json.program.lastUpdated);
    setInnerHTML('codeField', json.program.code);
    setInnerHTML('idField', json.program.uid);

    showDetails();
  });
}

// -----------------------------------------------------------------------------
// Remove Program
// -----------------------------------------------------------------------------

function removeProgram( context ) {
  removeItem(context.id, context.name, i18n_confirm_delete, 'removeProgram.action');
}

