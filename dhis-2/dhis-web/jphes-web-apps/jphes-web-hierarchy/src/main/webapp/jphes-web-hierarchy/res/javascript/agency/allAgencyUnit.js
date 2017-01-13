jQuery(document).ready(function() {
  tableSorter('listTable');

  dhis2.contextmenu.makeContextMenu({
    menuId: 'contextMenu',
    menuItemActiveClass: 'contextMenuItemActive'
  });
});

function showUpdateAgencyUnitForm( context ) {
  location.href = 'showUpdateAgencyUnitForm.action?id=' + context.id;
}

function showAddMechanismUnitForm( context ) {
  location.href = 'showAddMechanismUnitForm.action?id=' + context.id;
}
