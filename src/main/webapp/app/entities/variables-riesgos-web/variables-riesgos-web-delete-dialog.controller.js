(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('Variables_riesgos_webDeleteController',Variables_riesgos_webDeleteController);

    Variables_riesgos_webDeleteController.$inject = ['$uibModalInstance', 'entity', 'Variables_riesgos_web'];

    function Variables_riesgos_webDeleteController($uibModalInstance, entity, Variables_riesgos_web) {
        var vm = this;

        vm.variables_riesgos_web = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Variables_riesgos_web.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
