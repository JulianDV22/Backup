const obtenerEvento = async() => {
    try {
        const respuesta = await axios.get('http://localhost:8080/publiuco/api/v1')

        console.log(respuesta);
    } catch (error) {
        console.log(error);
    }
};

obtenerEvento();