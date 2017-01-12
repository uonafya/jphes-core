jQuery(document).ready(function() {
  tableSorter('listTable');

  dhis2.contextmenu.makeContextMenu({
    menuId: 'contextMenu',
    menuItemActiveClass: 'contextMenuItemActive'
  });
});

function showUpdateDonorUnitForm( context ) {
  location.href = 'showUpdateDonorUnitForm.action?id=' + context.id;
}
