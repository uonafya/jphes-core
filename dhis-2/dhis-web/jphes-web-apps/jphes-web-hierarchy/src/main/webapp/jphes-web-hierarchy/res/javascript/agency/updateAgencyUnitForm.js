jQuery( document ).ready( function()
{
  jQuery( "#name" ).focus();

  validation2( 'updateAgencyUnitForm', function( form )
  {
    selectAllById( 'selectedProgramList' );
    form.submit();
  }, {
    'rules' : getValidationRules("agencyUnit")
  } );

  /* remote validation */
  checkValueIsExist( "name", "validateAgencyUnit.action", {
    id : getFieldValue( 'id' )
  } );

  checkValueIsExist( "shortName", "validateAgencyUnit.action", {
    id : getFieldValue( 'id' )
  } );

  checkValueIsExist( "code", "validateAgencyUnit.action", {
    id : getFieldValue( 'id' )
  } );

  sortList( 'availableProgramList', 'ASC' );
} );