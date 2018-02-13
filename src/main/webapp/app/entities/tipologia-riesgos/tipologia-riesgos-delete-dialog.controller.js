(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('Tipologia_riesgosDeleteController',Tipologia_riesgosDeleteController);

    Tipologia_riesgosDeleteController.$inject = ['$uibModalInstance', 'entity', 'Tipologia_riesgos'];

    function Tipologia_riesgosDeleteController($uibModalInstance, entity, Tipologia_riesgos) {
        var vm = this;

        vm.tipologia_riesgos = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Tipologia_riesgos.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
