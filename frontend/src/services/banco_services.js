import Api from "./Api";

export const banco_lista_all = async () =>{
    try {
        const resp = await Api.get(`/banco/listall`);
        return resp
    } catch (error) {
        console.error(error);
    }
}

export const banco_delete = async (id) => {
    try {
        const resp = await Api.delete(`/banco/${id}`);
        console.log('banco_delete.resp', resp.data);
        return resp
    } catch (error) {
        console.error(error);
    }
}

export const banco_add = async (obj) => {
    console.log('banco_add', obj);
    try {
        const resp = await Api.post(`/banco`, obj);
        console.log('banco_add.resp', resp);
        return resp
    } catch (error) {
        console.error('Erro no banco_add:', error);
        throw error;
    }
}

export const banco_find_by_id = async (id) => {
    try {
        const resp = await Api.get(`/banco/findById/${id}`);
        console.log('banco_find_by_id', resp);
        return resp
    } catch (error) {
        console.error(error);
    }
}

export const banco_update = async (obj) => {
    console.log('banco_update', obj);
    try {
        const resp = await Api.put(`/banco/${obj.id_banco}`, obj);
        console.log('banco_update.resp', resp);
        return resp
    } catch (error) {
        console.error('Erro no banco_update: ', error);
        throw error;
    }
}