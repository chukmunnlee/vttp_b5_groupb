const { MeterProvider } = require('@opentelemetry/sdk-metrics')
const { PrometheusExporter } = require('@opentelemetry/exporter-prometheus')
const { NodeSDK } = require('@opentelemetry/sdk-node')
const { getNodeAutoInstrumentations } = require('@opentelemetry/auto-instrumentations-node')

module.exports = function(port) {
	const meterProvider = new MeterProvider();
	const exporter = new PrometheusExporter({ port , preventServerStart: true })
	meterProvider.addMetricReader(exporter)
	const meter = meterProvider.getMeter('dov-bear')
	const sdk = new NodeSDK({
		metricReader: exporter,
		instrumentations: [ getNodeAutoInstrumentations() ]
	})

	return { meter, exporter, sdk }
}

