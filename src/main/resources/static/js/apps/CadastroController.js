/**
 * Created by Y.Kamesh on 4/13/2015.
 */
angular.module('App')
    .controller('CadastroController', ['$scope', '$rootScope', '$location', 'AuthService', CadastroController]).filter('priceGreaterThan', function () {
        
           return function (input, price) {
               
               var output = [];
               var returns = [];
               $.each( $('#buscarQuadra-container3 .badgebox'), function( key, value ) {
                // console.log( key + ": " + value );
               if($(this).is(":checked")){
                 console.log($(this).val());
                 output.push($(this).val())}
               })
               for(var x = 0 ; x < input.length;x++)
               {
                    if( $.inArray(input[x].dia, output) !== -1 ){
                        returns.push(input[x]);
                    }
               }
          /*     
               returns.sort(function(a, b){
                   
                a = parseInt(a.dia);
                b = parseInt(b.dia);
                return a - b;
            });
*/
               return returns;
           }
       })


function CadastroController($scope, $rootScope, $location, AuthService) {
    var lc = this;
    lc.import  = function () {
        AuthService.uploadFiles();
    }
    lc.empresa = {
        endereco : {
            estado : {id : 1},
            cep:'38082243',
            logradouro:'herminio pinti',
            bairro:"vila alvorada",
            numero:'199',
            cidade:'Uberaba',
            referencia:'Proximo a fazu',
            estadoId : 1
        }, 
        quadras : [{nome:"nome",descricao : "descricao",valor : 1.99,comBola : 1,valorBola : 0.51,horarioAberto:[{abertura : "",fecha:""}]}],
        nome:'Quadra 0001',
        nomeResponsavel:'Responsavel 0001',
        email:'wlclimaco@gmail.com',
        telefone:'34988406670'
        
    }
    lc.createForm  = function (oQuadra) {
        
        oQuadra.horarioAberto.push({abertura : "",fecha:""});
    }

    lc.createFormQuadra  = function () {
        
        lc.empresa.quadras.push({comBola : 1,horarioAberto : [{abertura : "",fecha:""}]});
    }

    lc.login = function () {
        
        console.log('received the login event for user: '+lc.empresa.nome);
        lc.dataLoading = true;
        $rootScope.isSubmitted = true;
        
        $.get("http://maps.google.com/maps/api/geocode/json?address="+lc.empresa.endereco.logradouro+"+"+lc.empresa.endereco.numero+"+"+lc.empresa.endereco.bairro+",+"+lc.empresa.endereco.cidade+"+-+mg&sensor=false", function(data, status){
            console.log(data.results[0].geometry.location)

            lc.empresa.endereco.lat   = data.results[0].geometry.location.lat;
            lc.empresa.endereco.longi = data.results[0].geometry.location.lng;

            var oQuadras = [];
            for(var x=0;x<lc.empresa.quadras.length;x++)
            {
                oQuadras.push(new qat.model.quadra(lc.empresa.quadras[x]));
            }
            
            lc.empresa.quadras = [];
            lc.empresa.quadras = oQuadras;
            AuthService.addEmpresa(lc.empresa, function (response) {
                
                var resp = response.data;
                if (resp && resp.code==200) {
                    AuthService.createJWTToken(resp.result.user, resp.result.token);
                    AuthService.setCredentials();
                //   $location.path('/app');
                } else {
                    lc.error = resp.result;
                    lc.details = resp.details;
                    lc.dataLoading = false;
                    $rootScope.isSubmitted = false;
                }
            });
        });
    };
};