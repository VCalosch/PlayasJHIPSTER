(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('Situaciones_riesgosDeleteController',Situaciones_riesgosDeleteController);

    Situaciones_riesgosDeleteController.$inject = ['$uibModalInstance', 'entity', 'Situaciones_riesgos'];

    function Situaciones_riesgosDeleteController($uibModalInstance, entity, Situaciones_riesgos) {
        var vm = this;

        vm.situaciones_riesgos = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Situaciones_riesgos.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
