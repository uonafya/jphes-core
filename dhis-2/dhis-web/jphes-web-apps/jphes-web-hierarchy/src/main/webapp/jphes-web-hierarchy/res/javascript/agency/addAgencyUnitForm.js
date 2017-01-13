jQuery( document ).ready( function()
{
  jQuery( "#name" ).focus();

  validation2( 'addAgencyUnitForm', function( form )
  {
    selectAllById( 'selectedProgramList' );
    form.submit();
  }, {
    'rules' : getValidationRules("agencyUnit")
  } );

  /* remote validation */
  checkValueIsExist( "name", "validateAgencyUnit.action" );
  checkValueIsExist( "code", "validateAgencyUnit.action" );
  checkValueIsExist( "shortName", "validateAgencyUnit.action" );

  sortList( 'availableProgramList', 'ASC' );
} );
