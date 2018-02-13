(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('Tipologia_riesgosDialogController', Tipologia_riesgosDialogController);

    Tipologia_riesgosDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Tipologia_riesgos', 'Riesgos'];

    function Tipologia_riesgosDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Tipologia_riesgos, Riesgos) {
        var vm = this;

        vm.tipologia_riesgos = entity;
        vm.clear = clear;
        vm.save = save;
        vm.riesgos = Riesgos.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.tipologia_riesgos.id !== null) {
                Tipologia_riesgos.update(vm.tipologia_riesgos, onSaveSuccess, onSaveError);
            } else {
                Tipologia_riesgos.save(vm.tipologia_riesgos, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('playasApp:tipologia_riesgosUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
