jQuery( document ).ready( function()
{
  jQuery( "#name" ).focus();

  validation2( 'updateNationalUnitForm', function( form )
  {
    selectAllById( 'selectedProgramList' );
    form.submit();
  }, {
    'rules' : getValidationRules("nationalUnit")
  } );

  /* remote validation */
  checkValueIsExist( "name", "validateNationalUnit.action", {
    id : getFieldValue( 'id' )
  } );

  checkValueIsExist( "shortName", "validateNationalUnit.action", {
    id : getFieldValue( 'id' )
  } );

  checkValueIsExist( "code", "validateNationalUnit.action", {
    id : getFieldValue( 'id' )
  } );

  sortList( 'availableProgramList', 'ASC' );
} );