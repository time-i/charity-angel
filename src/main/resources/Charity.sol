pragma solidity ^0.4.20;

contract Charity {
    struct Transaction{
        string from;
        string to;
        uint amount;
    }

    Transaction[] Transactions;

    function _addTransactions(string _from, string _to, uint _amount) public{
        Transactions.push(Transaction(_from, _to, _amount));
    }

    function _selectAllAmountByFrom(string _from) public returns(uint) {
        uint sum = 0;
        bytes32 fromHash = keccak256(_from);
        for(uint i = 0; i < Transactions.length; i++) {
            if(fromHash == keccak256(Transactions[i].from)) {
                sum += Transactions[i].amount;
            }
        }
        return sum;
    }

    function _selectAllAmountByTo(string _to) public returns(uint) {
        uint sum = 0;
        bytes32 toHash = keccak256(_to);
        for(uint i = 0; i < Transactions.length; i++) {
            if(toHash == keccak256(Transactions[i].to)) {
                sum += Transactions[i].amount;
            }
        }
        return sum;
    }

}