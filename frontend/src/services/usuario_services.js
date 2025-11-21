import Api from "./Api";

export const usuario_login = async (login, password) => {
  try {
    var obj = { 
        login: login,
        senha: password
    }
    console.log("usuario_login.obj: ", obj);
    const resp = await Api.post(`/usuario/login`, obj);
    console.log("Resposta bruta da API:", resp);
    return resp;
  } catch (error) {
    console.error("Erro na API usuario_login:", error);
    return [];
  }
}
