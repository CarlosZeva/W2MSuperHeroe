package com.crud.superheroe.util;
import io.swagger.annotations.ApiModelProperty;

public class StandarizedApiExceptionResponse{
   @ApiModelProperty(notes = "El identificador de uri único que categoriza el error", name = "tipo", 
           required = true, example = "/errors/authentication/not-authorized")
    private String type ="/errors/uncategorized";
    @ApiModelProperty(notes = "Un mensaje de error breve y legible para usuario final", name = "titulo", 
           required = true, example = "El usuario no tiene autorización")
    private String title;
     @ApiModelProperty(notes = "El código de error único", name = "code", 
           required = false, example = "192")
    private String code;
      @ApiModelProperty(notes = "Un mensaje de error breve y legible para usuario final", name = "detalle", 
           required = true, example = "El usuario no tiene los permisos para acceder al "
                   + "recurso. Por favor contactar con Carlos Zevallos Soto (Perú)")
    private String detail;
       @ApiModelProperty(notes = "Un URI que específica el error.", name = "detail", 
           required = true, example = "/errors/authentication/not-authorized/01")
    private String instance ="/errors/superheroe";

    public StandarizedApiExceptionResponse(String title, String code, String detail) {
        super();
        this.title = title;
        this.code = code;
        this.detail = detail;
    }       
       
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

   

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return code;
    }

    public void setStatus(String status) {
        this.code = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
}
