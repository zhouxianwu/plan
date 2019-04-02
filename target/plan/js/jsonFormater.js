var JSONFomartter = {
//主方法，参数1为你需要转换的json对象，注意是对象而不是字符串，第二个参数则是你希望的缩进表示字符串，例如你可传进"    "四个空格作为缩进标准
    format:function(jsonObject, indexStr) {
        //如果未传缩进标准参数，则按默认处理
        if (arguments.length < 2) {
            var indexStr = "";
        }
        var indexStrStyle = "    ";
        //通过调用方法getType获取json对象的类型，例如数组
        var objectType = this.getType(jsonObject);
alert(objectType);
        // 根据json对象类型分情况处理
        if (objectType == "array") {
            if (jsonObject.length == 0) {
                return "[]";
            }
            var sHTML = "[";
        }else if(objectType == "String"){

        }
        else {
            var iCount = 0;
            $.each(jsonObject, function() {
                iCount++;
                return;
            });
            if (iCount == 0) { // object is empty
                return "{}";
            }
            var sHTML = "{";
        }

        // 遍历对象中的key和value并添加缩进
        var iCount = 0;
        $.each(jsonObject, function(sKey, vValue) {
            if (iCount > 0) {
                sHTML += ",";
            }
            if (objectType == "array") {
                sHTML += ("\n" + indexStr + indexStrStyle);
            } else {
                sHTML += ("\n" + indexStr + indexStrStyle + "\"" + sKey + "\"" + ": ");
            }

            // display relevant data type
            switch (this.getType(vValue)) {
                case "array":
                case "object":
                    sHTML += FormatJSON(vValue, (indexStr + indexStrStyle));
                    break;
                case "boolean":
                case "number":
                    sHTML += vValue.toString();
                    break;
                case "null":
                    sHTML += "null";
                    break;
                case "string":
                    sHTML += ("\"" + vValue + "\"");
                    break;
                default:
                    sHTML += ("TYPEOF: " + typeof(vValue));
            }
            iCount++;
        });

        if (objectType == "array") {
            sHTML += ("\n" + indexStr + "]");
        } else {
            sHTML += ("\n" + indexStr + "}");
        }

        return sHTML;
    },

//获取对象类型
    getType:function(obj) {
        if (typeof(obj) == "object") {
            if (obj === null) return "null";
            if (obj.constructor == (new Array).constructor) return "array";
            if (obj.constructor == (new Date).constructor) return "date";
            if (obj.constructor == (new RegExp).constructor) return "regex";
            return "object";
        }
        return typeof(obj);
    }
}