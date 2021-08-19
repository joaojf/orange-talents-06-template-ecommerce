package com.zup.edu.mercadolivre.compra;

import com.zup.edu.mercadolivre.pagamento.formaspagamento.Financeiro;
import com.zup.edu.mercadolivre.pagamento.formaspagamento.PagSeguro;
import com.zup.edu.mercadolivre.pagamento.formaspagamento.Paypal;

public enum EnumGatewayPagamento {


    PAGSEGURO {
        @Override
        public Financeiro financeiro() {
            return new PagSeguro();
        }
    },

    PAYPAL {
        @Override
        public Financeiro financeiro() {
            return new Paypal();
        }
    };

    abstract public Financeiro financeiro();
}
