pragma solidity ^0.4.20;

contract Charity {

    uint pragmaId;
    string pragmaName;
    string deadLine;
    string transTime;
    uint amount;

    constructor(uint _pragmaId, string _pragmaName, string _deadLine,string _transTime, uint _amount) {
        pragmaId = _pragmaId;
        pragmaName = _pragmaName;
        deadLine = _deadLine;
        transTime = _transTime;
        amount = _amount;
    }

    struct Transaction{
        uint id;
        string from;
        string to;
        uint amount;
        uint time;
    }

    Transaction[] Transactions;

    function _addTransactions(uint _id, string _from, string _to, uint _amount) public{
        Transactions.push(Transaction(_id, _from, _to, _amount, now));
    }

    function _selectInfoById(uint _id) public returns(string memory) {
        uint i;
        for(i = 0; i < Transactions.length; i++) {
            if(Transactions[i].id == _id) {
                break;
            }
        }
        string memory property_value;
        int property_type = 0;
 
        // 第一步调用 property_parse 
        uint handler = property_parse(property_value, property_type);
        // 然后设置各属性值
        show(handler, Transactions[i].from, "from");
        show(handler, Transactions[i].to, "to");
        showuint(handler, Transactions[i].amount, "amount");
        showuint(handler, Transactions[i].time, "time");

        property_type = 0;
        // 生成 json
        string memory ret = property_write(handler, property_type);
        property_destroy(handler);
        return ret;

    }

    // 1 -> "/u00001"

    function _showInfoByFrom(string _from) public returns(string memory) {
        string memory property_value;
        int property_type = 0;
        string memory tp = "[";

        uint handler = property_parse(property_value, property_type);
        bytes32 fromHash = keccak256(_from);
        uint flag = 0;
        for(uint i = 0; i < Transactions.length; i++) {
            if(fromHash == keccak256(Transactions[i].from)) {
                string memory property_value1;
                int property_type1 = 0;
                string memory path = uintToString(i + 1);
                string memory val;
                uint handler1 = property_parse(property_value1, property_type1);
                showuint(handler1, Transactions[i].id, "Transanction id");
                show(handler1, Transactions[i].to, "to");
                showuint(handler1, Transactions[i].amount, "amount");
                showuint(handler1, Transactions[i].time, "time");
                property_type1 = 0;
                val = property_write(handler1, property_type1);
                if(flag == 0){
                    tp = stringAdd(tp,val);
                    flag = 1;
                }
                else {
                    tp = stringAdd(tp,",");
                    tp = stringAdd(tp,val);
                }
                property_destroy(handler1);
                property_set_string(handler, path, val);
            }
        }
        tp = stringAdd(tp,"]");
        property_type = 0;
        // 生成json
        string memory ret = property_write(handler, property_type);
        property_destroy(handler);
        return tp; 
    }

    function _showInfoByTo(string _to) public returns(string memory) {
        string memory property_value;
        int property_type = 0;
        string memory tp = "[";
        uint flag = 0;

        uint handler = property_parse(property_value, property_type);
        bytes32 toHash = keccak256(_to);
        for(uint i = 0; i < Transactions.length; i++) {
            if(toHash == keccak256(Transactions[i].to)) {
                string memory property_value1;
                int property_type1 = 0;
                string memory path =uintToString(i + 1);
                string memory val;
                uint handler1 = property_parse(property_value1, property_type1);
                showuint(handler1, Transactions[i].id, "Transanction id");
                show(handler1, Transactions[i].from, "from");
                showuint(handler1, Transactions[i].amount, "amount");
                showuint(handler1, Transactions[i].time, "time");
                property_type1 = 0;
                val = property_write(handler1, property_type1);
                if(flag == 0){
                    tp = stringAdd(tp,val);
                    flag = 1;
                }
                else {
                    tp = stringAdd(tp,",");
                    tp = stringAdd(tp,val);
                }
                property_destroy(handler1);
                property_set_string(handler, path, val);
            }
        }
        tp = stringAdd(tp,"]");
        property_type = 0;
        // 生成json
        string memory ret = property_write(handler, property_type);
        property_destroy(handler);
        return tp; 
    }

    function show(uint handler, string _info, string _val) private returns(bool) {
        string memory val;
        val = _val;
        string memory info = _info;
        return  property_set_string(handler, val, info);
    }

    function showuint(uint handler, uint _info, string _val) private returns(bool) {
        string memory val;
        val = _val;
        uint  info = _info;
        return property_set_uint(handler, val, info);
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

    function uintToString(uint v) constant returns (string str) {
        uint maxlength = 100;
        bytes memory reversed = new bytes(maxlength);
        uint i = 0;
        while (v != 0) {
            uint remainder = v % 10;
            v = v / 10;
            reversed[i++] = byte(48 + remainder);
        }
        i--;
        bytes memory s = new bytes(i + 1);
        for (uint j = 0; j <= i; j++) {
            s[j] = reversed[i - j];
        }
        str = string(s);
    }
    //拼接字符串
    function stringAdd(string a, string b) returns(string){
        bytes memory _a = bytes(a);
        bytes memory _b = bytes(b);
        bytes memory res = new bytes(_a.length + _b.length);
        for(uint i = 0;i < _a.length;i++)
            res[i] = _a[i];
        for(uint j = 0;j < _b.length;j++)
            res[_a.length+j] = _b[j];
        return string(res);
    }
}