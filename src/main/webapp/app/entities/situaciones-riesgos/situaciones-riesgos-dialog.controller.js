(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('Situaciones_riesgosDialogController', Situaciones_riesgosDialogController);

    Situaciones_riesgosDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Situaciones_riesgos'];

    function Situaciones_riesgosDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Situaciones_riesgos) {
        var vm = this;

        vm.situaciones_riesgos = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.situaciones_riesgos.id !== null) {
                Situaciones_riesgos.update(vm.situaciones_riesgos, onSaveSuccess, onSaveError);
            } else {
                Situaciones_riesgos.save(vm.situaciones_riesgos, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('playasApp:situaciones_riesgosUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.fecha = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
