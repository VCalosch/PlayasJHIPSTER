(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('RiesgosDeleteController',RiesgosDeleteController);

    RiesgosDeleteController.$inject = ['$uibModalInstance', 'entity', 'Riesgos'];

    function RiesgosDeleteController($uibModalInstance, entity, Riesgos) {
        var vm = this;

        vm.riesgos = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Riesgos.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
