jQuery( document ).ready( function()
{
  jQuery( "#name" ).focus();

  validation2( 'addNationalUnitForm', function( form )
  {
    selectAllById( 'selectedList' );
    selectAllById( 'selectedListPrograms' );
    form.submit();
  }, {
    'rules' : getValidationRules("nationalunit")
  } );

  /* remote validation */
  checkValueIsExist( "name", "validateNationalUnit.action" );

  sortList( 'availableListProgram', 'ASC' );
} );
