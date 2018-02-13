(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('RiesgosDialogController', RiesgosDialogController);

    RiesgosDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Riesgos'];

    function RiesgosDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Riesgos) {
        var vm = this;

        vm.riesgos = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.riesgos.id !== null) {
                Riesgos.update(vm.riesgos, onSaveSuccess, onSaveError);
            } else {
                Riesgos.save(vm.riesgos, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('playasApp:riesgosUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
