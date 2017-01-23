jQuery(document).ready(function() {
  tableSorter('listTable');

  dhis2.contextmenu.makeContextMenu({
    menuId: 'contextMenu',
    menuItemActiveClass: 'contextMenuItemActive'
  });
});

function showUpdateMechanismUnitForm( context ) {
  location.href = 'showUpdateMechanismUnitForm.action?id=' + context.id;
}
